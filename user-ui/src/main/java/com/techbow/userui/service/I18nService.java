package com.techbow.userui.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class I18nService {

//    private static final String URL = "http://localhost:8083";
    private static final String URL = "http://i18n-service";

    @Autowired
    private RestTemplate restTemplate;

    private static final String I18nServiceLoadControl = "i18n_service";

    @Bulkhead(name = I18nServiceLoadControl)
    @Retry(name = I18nServiceLoadControl)
    public String getTranslation(String keyword) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("keyword", keyword);

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/{keyword}").buildAndExpand(urlParams);
        return restTemplate.getForObject(uriComponents.toUriString(), String.class);
    }

    public String getTranslation(String keyword, String locale) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("keyword", keyword);
        urlParams.put("locale", locale);

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/{keyword}/{locale}").buildAndExpand(urlParams);
        return restTemplate.getForObject(uriComponents.toUriString(), String.class);
    }
}
