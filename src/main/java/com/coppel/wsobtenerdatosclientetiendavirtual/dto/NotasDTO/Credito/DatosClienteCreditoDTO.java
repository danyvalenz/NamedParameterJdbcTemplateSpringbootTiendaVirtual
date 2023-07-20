package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatosClienteCreditoDTO {
    private Integer num_clienteCredito;
    private String nom_clienteCredito;
    private Integer imp_nuevoabonoropa;
    private String des_tipopago;
    private Integer num_empleado;

}
