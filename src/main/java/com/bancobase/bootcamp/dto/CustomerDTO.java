package com.bancobase.bootcamp.dto;

import com.bancobase.bootcamp.schemas.AccountSchema;
import com.bancobase.bootcamp.schemas.CustomerSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerDTO {
    private CustomerInfoDTO information;
    private List<AccountDTO> accounts;

    public static CustomerDTO getFromSchema(CustomerSchema customerSchema, List<AccountSchema> accountSchemas) {
        List<AccountDTO> accounts = accountSchemas
                .stream()
                .map(AccountDTO::getFromSchema)
                .toList();

        return CustomerDTO
                .builder()
                .information(CustomerInfoDTO.getFromSchema(customerSchema))
                .accounts(accounts)
                .build();
    }
}
