package org.soyphea.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        logRequestDetails(request);
        return execution.execute(request, body);
    }

    @Async
    private void logRequestDetails(HttpRequest request) {
        log.info("Made a http request with Headers: {} Msethod:{} URI:{}", request.getHeaders(),request.getMethod(),request.getURI());
    }

}
