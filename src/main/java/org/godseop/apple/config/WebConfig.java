package org.godseop.apple.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.godseop.apple.security.HtmlCharacterEscapes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

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
            .addResolver(pathResourceResolver());
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
    public MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

    @Bean
    public PathResourceResolver pathResourceResolver() {
        return new PathResourceResolver();
    }



}
