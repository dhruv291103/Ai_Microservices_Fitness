package com.fitness.activityservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Getter
@Service
@RequiredArgsConstructor
@Slf4j   // to use log
public class UserValidationService {
    private final WebClient UserServiceWebClient;

    public boolean validateUser(String userId){
        log.info("Calling user service for {}", userId);
        try{
            return UserServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch(WebClientResponseException e){
            e.printStackTrace();
        }
        return false;
    }

}
