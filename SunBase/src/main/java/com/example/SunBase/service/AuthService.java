package com.example.SunBase.service;

import com.example.SunBase.models.AuthRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final WebClient webClient;

    public AuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://qa.sunbasedata.com").build();
    }

    public Mono<String> getToken(String loginId, String password) {
        return this.webClient.post()
                .uri("/sunbase/portal/api/assignment_auth.jsp")
                .bodyValue(new AuthRequest(loginId, password))
                .retrieve()
                .bodyToMono(String.class);
    }


}
