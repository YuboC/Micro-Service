package com.techbow.userui.service;

import com.techbow.userui.model.User;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
//    private static final String URL = "http://localhost:8082";
    private static final String URL = "http://user-crud-service";


    @Autowired
    private RestTemplate restTemplate;

    private static final String I18nServiceLoadControl = "i18n_service";

    @CircuitBreaker(name = I18nServiceLoadControl)
    @Bulkhead(name = I18nServiceLoadControl)
    @Retry(name = I18nServiceLoadControl)
    public List<User> getAllUsers() {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/user").buildAndExpand();
        ResponseEntity<User[]> response = restTemplate.getForEntity(uriComponents.toUriString(), User[].class);
        return Arrays.asList(response.getBody());
    }

    public User saveUser(User user) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/user").buildAndExpand();
        return restTemplate.postForEntity(uriComponents.toUriString(), user, User.class).getBody();
    }

    public User getUser(Long id) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", Long.toString(id));
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/user/{id}").buildAndExpand(urlParams);
        return restTemplate.getForObject(uriComponents.toUriString(), User.class);
    }

    public User updateUser(User user) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/user").buildAndExpand();
        return restTemplate.exchange(uriComponents.toUriString(), HttpMethod.PUT, new HttpEntity<>(user), User.class).getBody();
    }

    public void deleteUser(Long id) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", Long.toString(id));
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/user/{id}").buildAndExpand(urlParams);
        restTemplate.delete(uriComponents.toUriString());
    }
}
