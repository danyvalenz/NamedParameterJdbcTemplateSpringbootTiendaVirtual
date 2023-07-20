package com.coppel.wsobtenerdatosclientetiendavirtual.controllers;

import com.coppel.wsobtenerdatosclientetiendavirtual.services.Tienda800Service;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800.DetalleRopa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class InventarioEcomm {

    @Autowired
    private Tienda800Service tda800Service;

    @PostMapping("Tda800/ActualizarInventario")
    public boolean actualizarInventario(@RequestParam Integer tienda, @RequestBody List<DetalleRopa> ldr) {

        return tda800Service.actualizaInventarioEcomm(tienda,ldr);
    }
}
