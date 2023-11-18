package com.wobhomework.project.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Repository
public class ServiceUtil {

    private final WebClient.Builder webClientBuilder;

    public Object restCall(String url, Object[] object) {
        try {
            return webClientBuilder.build().get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(object.getClass())
                    .block();
        } catch (Exception e) {
            return object;
        }

    }

}
