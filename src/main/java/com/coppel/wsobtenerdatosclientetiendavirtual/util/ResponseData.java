package com.coppel.wsobtenerdatosclientetiendavirtual.util;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ResponseData
 */
@AllArgsConstructor
@Data
public class ResponseData<T> {
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("mensaje")
    private  String mensaje;
    
    @JsonProperty("data")
    private DatosClienteDTO data;

    public  ResponseData() {
        this.status = STATUS.Status;
        this.mensaje = STATUS.Mensaje;
        this.data = null;
    }

}