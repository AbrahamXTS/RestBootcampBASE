package com.bancobase.bootcamp.dto;

import com.bancobase.bootcamp.schemas.CustomerSchema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerInfoDTO {
    private Long customerId;
    private String name;
    private String curp;
    private String gender;
    private Date birthdate;

    public static CustomerInfoDTO getFromSchema(CustomerSchema customerSchema) {
        return CustomerInfoDTO
                .builder()
                .birthdate(customerSchema.getBirthdate())
                .curp(customerSchema.getCurp())
                .customerId(customerSchema.getCustomerId())
                .gender(customerSchema.getGender())
                .name(customerSchema.getName())
                .build();
    }
}
