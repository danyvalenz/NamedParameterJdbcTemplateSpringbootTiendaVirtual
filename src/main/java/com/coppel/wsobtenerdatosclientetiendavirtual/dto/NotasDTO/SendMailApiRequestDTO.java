package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SendMailApiRequestDTO {
    private Object options;
    private Integer tipoPago;
    private String email;

    public SendMailApiRequestDTO(Object options, Integer tipoPago, String email) {
        this.options = options;
        this.tipoPago = tipoPago;
        this.email = email;
    }
}
