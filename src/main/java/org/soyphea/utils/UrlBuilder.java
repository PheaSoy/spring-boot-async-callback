package org.soyphea.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlBuilder {

    @Value("${stackoverflow.api.base-url}")
    String stackOverflowBaseURL;

    @Value("${stackoverflow.api.tag}")
    String getStackOverflowApiTag;

    public String fullApiEndpoint(String userId) {

        StringBuilder api = new StringBuilder(stackOverflowBaseURL);
        api.append(getStackOverflowApiTag);
        String endpoint =  api.toString().replace("{user_id}",userId);
        return endpoint;
    }
}
