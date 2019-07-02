package org.godseop.apple.config;

import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate defaultRestTemplate() {
        return restTemplateBuilder.rootUri("http://localhost:8080")
                .additionalInterceptors(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(@NonNull final HttpRequest request,
                                                        @NonNull final  byte[] body,
                                                        @NonNull final ClientHttpRequestExecution execution) throws IOException {

                        loggingRequest(request, body);
                        final ClientHttpResponse response = execution.execute(request, body);
                        loggingResponse(response);

                        return response;
                    }

                    private void loggingRequest(final HttpRequest request, final byte[] body) {
                        log.info("defaultRestTemplate Request intercept ================================================");
                        log.info("Headers         : {}", request.getHeaders());
                        log.info("Request Method  : {}", request.getMethod());
                        log.info("Request URI     : {}", request.getURI());
                        log.info("Request body    : {}", (body.length == 0) ? null : new String(body, StandardCharsets.UTF_8));
                        log.info("======================================================================================");
                    }

                    private void loggingResponse(ClientHttpResponse response) throws IOException {
                        log.info("defaultRestTemplate Response intercept ===============================================");
                        log.info("Headers         : {}", response.getHeaders());
                        log.info("Response Status : {}", response.getRawStatusCode());
                        log.info("Response Body   : {}", IOUtils.toString(response.getBody()));
                        log.info("======================================================================================");
                    }


                })
                .errorHandler(new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(@NonNull final ClientHttpResponse response) throws IOException {
                        final HttpStatus statusCode = response.getStatusCode();
                        // 200번대 성공코드가 아닐때 return true
                        return !statusCode.is2xxSuccessful();
                    }

                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                        // hasError return true일때 실행됨
                        log.error("defaultRestTemplate handleError =====================================================");
                        log.error("Headers : {}", response.getHeaders());
                        log.error("Status  : {}", response.getStatusCode().value());
                        log.error("Body    : {}", IOUtils.toString(response.getBody()));
                        log.error("=====================================================================================");
                    }
                })
                .setConnectTimeout(Duration.ofMinutes(1))
                .build();
    }

}
