package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatosNotaDTO {
    private String fecha_factura;
    private Integer num_folio;
    private String nom_email;
    private Integer tipo_pago;
}
