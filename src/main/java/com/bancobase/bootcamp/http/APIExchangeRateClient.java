package com.bancobase.bootcamp.http;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class APIExchangeRateClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getExchangeRate() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.exchangerate.host/latest")
                .queryParam("base", "MXN")
                .toUriString();

        HttpEntity<String> headersAndBody = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = this.restTemplate
                .exchange(url, HttpMethod.GET, headersAndBody, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        return null;
    }
}
