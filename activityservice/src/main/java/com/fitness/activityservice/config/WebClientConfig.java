package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean    // using bean ,so that it is accessible throughout the application.
    @LoadBalanced  //will be using service name internally for inter process communication , so load balance is necessary.
//    {just like how we send request from postman same WebClient does, it is HTTP client}
//    {WebClient.builder it is like a factory which makes WebClient}
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }


    // ye service sirf userService ki API ko call krne ke liye banayi gayi hai
    @Bean    //Bean use krte hai , taki ye URL pure application pe accessible ho
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.baseUrl("http://USERSERVICE")
                .build();
    }
}
