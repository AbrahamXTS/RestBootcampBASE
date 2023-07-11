package com.bancobase.bootcamp.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PreCustomerInfo {
    private String name;
    private String curp;
    private String gender;
    private Date birthdate;
}
