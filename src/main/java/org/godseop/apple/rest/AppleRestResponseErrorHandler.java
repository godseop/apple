package org.godseop.apple.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AppleRestResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(@NonNull final ClientHttpResponse response) throws IOException {
        final HttpStatus statusCode = response.getStatusCode();

        if (statusCode.series() == HttpStatus.Series.SERVER_ERROR) {
            log.error("Response code series SERVERERROR... [{}]{}", statusCode.value(), statusCode.getReasonPhrase());
        }

        // 200번대 성공코드가 아닐때 return true
        return !statusCode.is2xxSuccessful();
    }

    @Override
    public void handleError(@NonNull  final ClientHttpResponse response) throws IOException {
        // hasError return true일때 실행됨
        log.error("TRACE Response handleError ==========================================================");
        log.error("Headers : {}", response.getHeaders());
        log.error("Status  : {}", response.getStatusCode().value());
        log.error("Body    : {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        log.error("=====================================================================================");
    }
}
