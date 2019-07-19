package org.godseop.apple.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.godseop.apple.component.AccessLogFilter;
import org.godseop.apple.component.CertificationInterceptor;
import org.godseop.apple.security.HtmlCharacterEscapes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.MultipartConfigElement;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CertificationInterceptor certificationInterceptor;

    @Bean
    public FilterRegistrationBean<MultipartFilter> registerMultipartFilter() {
        FilterRegistrationBean<MultipartFilter> multipartFilter = new FilterRegistrationBean<>();
        multipartFilter.setFilter(new MultipartFilter());
        multipartFilter.setOrder(1);
        multipartFilter.addUrlPatterns("/*");

        return multipartFilter;
    }

    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> registerXssFilter() {
        FilterRegistrationBean<XssEscapeServletFilter> xssFilter = new FilterRegistrationBean<>();
        xssFilter.setFilter(new XssEscapeServletFilter());
        xssFilter.setOrder(2);
        xssFilter.addUrlPatterns("/*");

        return xssFilter;
    }

    @Bean
    public FilterRegistrationBean<AccessLogFilter> registerAccessLogFilter() {
        FilterRegistrationBean<AccessLogFilter> accessLogFilter = new FilterRegistrationBean<>();
        accessLogFilter.setFilter(new AccessLogFilter());
        return accessLogFilter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(htmlEscapingConveter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/webapp/resources/**")
            .addResourceLocations("/css", "/js", "/images")
            .setCachePeriod(3600)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(certificationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/join", "/error/**");
    }


    @Bean
    public HttpMessageConverter<?> htmlEscapingConveter() {
        ObjectMapper objectMapper = new ObjectMapper();

        // ObjectMapper에 특수 문자 처리 기능 적용
        objectMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // MessageConverter에 ObjectMapper 설정
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        return converter;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofGigabytes(2));
        factory.setMaxRequestSize(DataSize.ofGigabytes(4));

        return factory.createMultipartConfig();
    }


}
