package org.soyphea.config;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRestTemplateCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(30000);
        restTemplate.setRequestFactory(requestFactory);
        //restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());
    }
}
