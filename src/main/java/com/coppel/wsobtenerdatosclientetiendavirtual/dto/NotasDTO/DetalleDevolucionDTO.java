package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetalleDevolucionDTO {

    private Integer num_codigo;
    private String num_talla;
    private Integer id_serial;
    private String nom_motivo;
    private Integer num_folio_devolucion;
    private String fecha_devolucion;

}
