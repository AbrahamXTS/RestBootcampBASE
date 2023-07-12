package com.bancobase.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

    @JsonProperty(value = "motd")
    private Motd motd;

    @JsonProperty(value = "success")
    private Boolean success;

    @JsonProperty(value = "base")
    private String base;

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "rates")
    private Map<String, Double> rates;
}
