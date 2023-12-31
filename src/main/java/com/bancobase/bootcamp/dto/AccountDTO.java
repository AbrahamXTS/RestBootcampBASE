package com.bancobase.bootcamp.dto;

import com.bancobase.bootcamp.schemas.AccountSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountDTO {
    private String accountNumber;
    private Double balance;

    public static AccountDTO getFromSchema(AccountSchema accountSchema) {
        return AccountDTO
                .builder()
                .accountNumber(accountSchema.getAccountNumber())
                .balance(accountSchema.getBalance())
                .build();
    }
}
