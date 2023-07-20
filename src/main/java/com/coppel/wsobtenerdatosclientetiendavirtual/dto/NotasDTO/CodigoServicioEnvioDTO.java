package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodigoServicioEnvioDTO {

    private Integer codigo;
    private Integer precioVenta;
    private String descripcion;
    private Integer area;

}
