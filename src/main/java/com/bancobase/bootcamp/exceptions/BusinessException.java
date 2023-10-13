package com.bancobase.bootcamp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final String message;
    private final String details;
}
