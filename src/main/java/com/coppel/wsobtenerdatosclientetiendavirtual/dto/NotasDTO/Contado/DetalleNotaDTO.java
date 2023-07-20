package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DetalleNotaDTO {
    private Integer imp_nuevoropa;
    private Integer imp_enganche;
    private Integer prec_unitario;
    private String nom_descripcion;
    private Integer num_codigo;
    private Integer num_talla;
    private Integer num_sobreprecio;
    private Integer num_cantidad;
    private Integer pago_dineroe;
    private Integer gano_dineroe;
    private Integer descuento;

}
