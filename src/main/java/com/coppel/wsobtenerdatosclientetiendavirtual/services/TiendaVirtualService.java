package com.coppel.wsobtenerdatosclientetiendavirtual.services;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.*;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DatosClienteCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DatosClienteDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.ResponseData;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface TiendaVirtualService
{

    ResponseData<com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO> obtenerInformacionCliente(Integer factura, Integer cedis);

    /* Notas Contado y Credito para la generacion de tickets */

    DatosNotaDTO obtenerDatosNota(Integer folioRopa);

    DatosClienteDTO obtenerDatosCliente(Integer folioRopa);

    DatosClienteCreditoDTO obtenerDatosClienteCredito(Integer folioRopa);

    String obtenerNombreArticulo(Integer codigo);

    Integer obtenerSaldoElectronico(Integer folioRopa);

    Object obtenerDetalleDevolucion(Integer folioRopa) throws SQLException;

}
