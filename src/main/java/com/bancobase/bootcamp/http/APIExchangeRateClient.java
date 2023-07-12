package com.bancobase.bootcamp.http;

import com.bancobase.bootcamp.dto.response.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class APIExchangeRateClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRateResponse getExchangeRate() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.exchangerate.host/latest")
                .queryParam("base", "MXN")
                .toUriString();

        HttpEntity<String> headersAndBody = new HttpEntity<>(headers);

        ResponseEntity<ExchangeRateResponse> responseEntity = this.restTemplate
                .exchange(url, HttpMethod.GET, headersAndBody, ExchangeRateResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        return null;
    }

    public SymbolsNameResponse getSymbolsName() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.exchangerate.host/symbols")
                .toUriString();

        HttpEntity<String> headersAndBody = new HttpEntity<>(headers);

        ResponseEntity<SymbolsNameResponse> responseEntity = this.restTemplate
                .exchange(url, HttpMethod.GET, headersAndBody, SymbolsNameResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        return null;
    }
}
