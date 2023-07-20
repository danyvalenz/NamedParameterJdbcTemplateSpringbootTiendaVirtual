package com.coppel.wsobtenerdatosclientetiendavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.devoluciones_texcoco.DetalleDevolucion;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatosClienteDTO
{
    private Integer num_factura;
    private Integer num_tienda;
    private Integer idu_pedido;
    private Integer num_cliente;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String nom_personarecibe;
    private String des_email;
    private String nom_calle;
    private String nom_entrecalles;
    private Integer num_casa;
    private String num_codigopostal;
    private String des_observaciones;
    private String num_interior;
    private String num_telefono;
    private String des_colonia;
    private String des_estado;
    private String des_ciudad;
    private Integer num_zona;
    private Integer num_ciudad;
    private Integer num_codigord;
    private String des_fechaPromesa;
    private String des_fechaVenta;
    //RD = ROPA DISTRIBUCION || EC = ENVIOCLICK
    private String des_tipo;

    //DEVOLUCIONES
    private Integer num_devolucion;
    private  boolean flag_devolucion;
    private Integer tipo_devolucion;
    private List<DetalleDevolucion> det_devolucion;
}
