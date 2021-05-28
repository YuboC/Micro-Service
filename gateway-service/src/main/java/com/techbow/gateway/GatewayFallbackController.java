package com.techbow.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewayFallbackController {
    @GetMapping
    public String fallback() {
        return "we are having issues";
    }
}
