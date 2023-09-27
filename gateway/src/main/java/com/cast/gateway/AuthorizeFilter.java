package com.cast.gateway;
/**
* @Author: mianmiantea2019
* @Date: 2023-09-23
* @LastEditors: mianmiantea2019
* @Description:
*/

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// @Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {    
   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       ServerHttpRequest request = exchange.getRequest();
       MultiValueMap<String, String> params = request.getQueryParams();
       String auth = params.getFirst("authorization");
       if ("admin".equals(auth)) {
           return chain.filter(exchange);
       }
       exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
       return exchange.getResponse().setComplete();
   }

   @Override
   public int getOrder() {
       return -1;
   }
}

