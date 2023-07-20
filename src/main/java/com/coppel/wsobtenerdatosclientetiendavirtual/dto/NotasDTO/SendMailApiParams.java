package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SendMailApiParams {
    private String name;
    private Object value;

    public SendMailApiParams(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
