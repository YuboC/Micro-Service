package com.techbow.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("Request - [Method: " + request.getMethod() + ", URL: " + request.getURI().toString() + "]");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Response - [Method: " + request.getMethod() + ", URL: " + request.getURI().toString() + "]");
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}