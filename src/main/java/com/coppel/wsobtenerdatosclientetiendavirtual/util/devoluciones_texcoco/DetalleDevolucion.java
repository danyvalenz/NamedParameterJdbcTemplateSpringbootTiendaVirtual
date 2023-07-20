package com.coppel.wsobtenerdatosclientetiendavirtual.util.devoluciones_texcoco;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDevolucion {
    private Integer codigo = 0;
    private Integer talla = 0;
    private Integer cantidad = 0;
    private Integer estatus = 0;
    @JsonProperty("des_codigo")
    private String desCodigo = "";
}
