package com.coppel.wsobtenerdatosclientetiendavirtual.util.devoluciones_texcoco;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionWrapper {
    @JsonProperty("devolucion_wrapper")
    public List<DetalleDevolucion> devolucionWrapper;
}
