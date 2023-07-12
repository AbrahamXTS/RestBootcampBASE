package com.bancobase.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SymbolsNameResponse {

    @JsonProperty(value = "motd")
    private Motd motd;

    @JsonProperty(value = "success")
    private Boolean success;

    @JsonProperty(value = "symbols")
    private Map<String, Symbol> symbols;
}
