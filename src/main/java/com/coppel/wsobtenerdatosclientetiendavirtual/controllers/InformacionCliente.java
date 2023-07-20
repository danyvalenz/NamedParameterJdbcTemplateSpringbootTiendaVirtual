package com.coppel.wsobtenerdatosclientetiendavirtual.controllers;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.services.TiendaVirtualService;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author daniel.valenzuela
 */


@RestController
@RequestMapping("/")
public class InformacionCliente {


    @Autowired
    private TiendaVirtualService tdaVirtualService;

    @GetMapping("TdaVirtual/DomicilioCliente")
    public ResponseData<DatosClienteDTO> obtenerInformacionCliente(@RequestParam int factura, @RequestParam int cedis) {

        return tdaVirtualService.obtenerInformacionCliente(factura,cedis);

    }

    @GetMapping("test")
    public ResponseData<String> test() {

        ResponseData<String> res = new ResponseData<>();
        res.setStatus(1);
        res.setMensaje("Hay conexion");
        res.setData(null);
        return res;
    }


}
