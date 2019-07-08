package org.godseop.apple.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AppleRestRequestInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(@NonNull final HttpRequest request,
                                        @NonNull final  byte[] body,
                                        @NonNull final ClientHttpRequestExecution execution) throws IOException {

        traceRequest(request, body);
        final ClientHttpResponse response = execution.execute(request, body);
        traceResponse(request, response);

        return response;
    }

    private void traceRequest(final HttpRequest request, final byte[] body) {
        log.info("TRACE Request intercept ==============================================================");
        log.info("Uri             : {}", request.getURI());
        log.info("Headers         : {}", request.getHeaders());
        log.info("Request Method  : {}", request.getMethod());
        log.info("Request body    : {}", (body.length == 0) ? null : new String(body, StandardCharsets.UTF_8));
        log.info("======================================================================================");
    }

    private void traceResponse(final HttpRequest request, ClientHttpResponse response) throws IOException {
        log.info("TRACE Response intercept ===============================================");
        log.info("Uri             : {}", request.getURI());
        log.info("Headers         : {}", response.getHeaders());
        log.info("Response Status : {}", response.getRawStatusCode());
        log.info("Response Body   : {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        log.info("======================================================================================");
    }
}

