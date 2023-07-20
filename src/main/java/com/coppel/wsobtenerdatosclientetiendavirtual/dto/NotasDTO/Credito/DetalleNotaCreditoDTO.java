package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetalleNotaCreditoDTO {
    private Integer imp_nuevoropa;
    private Integer imp_enganche;
    private Integer prec_unitario;
    private String nom_descripcion;
    private Integer num_codigo;
    private Integer num_talla;
    private Integer num_sobreprecio;
    private Integer num_cantidad;
    private Integer descuento;
    private Integer pago_dineroe;
    private Integer gano_dineroe;
    private Integer total_dineroe;
}
