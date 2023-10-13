package com.bancobase.bootcamp.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Symbol {
    private String description;
    private String code;
}
