package com.coppel.wsobtenerdatosclientetiendavirtual.services.impl;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.*;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DatosClienteCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DatosClienteDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.repositories.TiendaVirtualRepository;
import com.coppel.wsobtenerdatosclientetiendavirtual.services.TiendaVirtualService;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class TiendaVirtualServiceImpl implements TiendaVirtualService {
    //CONEXION TIENDA VIRTUAL
    @Autowired
    TiendaVirtualRepository tdaVirtualRepo;

    @Override
    public ResponseData<com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO> obtenerInformacionCliente(Integer factura, Integer cedis) {
        ResponseData<com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO> data = new ResponseData<>();

        com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO dto = null;
        try{
            dto = tdaVirtualRepo.obtenerInformacionCliente(factura,cedis);
            data.setStatus(1);
            data.setMensaje("Consulta exitosa!");
            data.setData(dto);


        }catch(EmptyResultDataAccessException emtpy){
            return new ResponseData<>();
        }
        catch (Exception e){
            throw e;
        }
        return data;
    }

    /* Notas Contado y Credito para la generacion de tickets */

    @Override
    public DatosNotaDTO obtenerDatosNota(Integer folioRopa) {
        DatosNotaDTO datosNotaDTO;
        datosNotaDTO = tdaVirtualRepo.obtenerDatosNota(folioRopa);
        return datosNotaDTO;
    }

    @Override
    public DatosClienteDTO obtenerDatosCliente(Integer folioRopa) {

        DatosClienteDTO datosClienteDTO = null;
        datosClienteDTO = tdaVirtualRepo.obtenerDatosCliente(folioRopa);
        return datosClienteDTO;
    }

    @Override
    public DatosClienteCreditoDTO obtenerDatosClienteCredito(Integer folioRopa) {

        DatosClienteCreditoDTO datosClienteCreditoDTO = null;
        datosClienteCreditoDTO = tdaVirtualRepo.obtenerDatosClienteCredito(folioRopa);
        return datosClienteCreditoDTO;
    }

    @Override
    public String obtenerNombreArticulo(Integer codigo) {
        String nombreArticulo = tdaVirtualRepo.obtenerNombreArticulo(codigo);
        return nombreArticulo;
    }

    @Override
    public Integer obtenerSaldoElectronico(Integer folioRopa) {
        Integer saldoe = null;
        saldoe = tdaVirtualRepo.obtenerSaldoElectronico(folioRopa);
        return saldoe;
    }

    @Override
    public Object obtenerDetalleDevolucion(Integer folioRopa) throws SQLException {

        Object detalleDevolucion;
        detalleDevolucion = tdaVirtualRepo.obtenerDetalleDevolucion(folioRopa);
        return detalleDevolucion;
    }

}
