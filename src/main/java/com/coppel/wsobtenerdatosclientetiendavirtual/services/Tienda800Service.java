package com.coppel.wsobtenerdatosclientetiendavirtual.services;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DetalleNotaDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DetalleNotaCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.ResponseDataDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800.DetalleRopa;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface Tienda800Service {
    boolean actualizaInventarioEcomm(Integer tienda, List<DetalleRopa> ldr);

    /* Notas Contado y Credito para la generacion de tickets */

    Object obtenerDetalleNota(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException;
    Object obtenerDetalleNotaCredito(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException;

}
