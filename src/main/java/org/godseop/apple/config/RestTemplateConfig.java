package org.godseop.apple.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.interceptor.AppleRestRequestInterceptor;
import org.godseop.apple.interceptor.AppleRestResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    @Bean(name = "appleRestTemplate")
    public RestTemplate appleRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setBufferRequestBody(true);
        factory.setConnectTimeout(10000);

        return restTemplateBuilder
                //.rootUri("http://localhost:8080")
                .requestFactory(() -> new BufferingClientHttpRequestFactory(factory))
                .additionalInterceptors(new AppleRestRequestInterceptor())
                .errorHandler(new AppleRestResponseErrorHandler())
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .setConnectTimeout(Duration.ofMinutes(5))
                .setReadTimeout(Duration.ofMinutes(5))
                .build();
    }
}
