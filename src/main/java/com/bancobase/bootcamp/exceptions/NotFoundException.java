package com.bancobase.bootcamp.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;
    private final String details;
}
