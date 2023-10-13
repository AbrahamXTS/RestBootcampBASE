package com.bancobase.bootcamp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
