package org.soyphea.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRestTemplateConfig {

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer(){
        return new MyRestTemplateCustomizer();
    }

    @Bean("bootifulRestTemplate")
    @Primary
    RestTemplate restTemplate() {
       return new RestTemplateBuilder(restTemplateCustomizer()).build();
    }
}
