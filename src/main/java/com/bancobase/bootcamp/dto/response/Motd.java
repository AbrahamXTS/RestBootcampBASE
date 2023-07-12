package com.bancobase.bootcamp.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Motd {
    private String msg;
    private String url;
}
