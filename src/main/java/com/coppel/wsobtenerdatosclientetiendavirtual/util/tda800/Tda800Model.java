package com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tda800Model {
    private Integer numbodega;
    private Integer totalUnidades;
    private Integer totalPesos;
    private String master_olpnid_guia;
    private Integer tienda;
    private Integer tipoguia;
    private Integer cantlotes;
    private Integer centro;
    private Integer jabasGrandes;
    private Integer jabasChicas;
    private Integer consecutivo;

    private List<String>  nomguia;
    private List<Integer> numlote;
    private List<Integer> numcodigo;
    private List<Integer> numtalla;
    private List<Integer> preciounitario;
    private List<Integer> cantidad;
    private List<Integer> preciomaximo;
    private List<String> marca;
    private List<String> esimportacion;
    private List<String> areacodigo;
    private List<Integer> numdepto;
    private List<Integer> numclase;
    private List<Integer> numfamilia;
    private List<Integer> esdescontinuado;
    private List<Integer> numcentro;
    private List<Integer> manejaiva;
    private List<Integer> precioventaminimo;
    private List<Integer> rebajado;
    private List<Integer> preciodescontinuado;
    private List<Integer> numempleado;
    private List<String> nomcentro;
    private List<Integer> imp_precioventa;
    private List<String> fechabaja;
    private List<Integer> id_importador;
    private List<String> desc_proveedor;
    private List<String> desc_larga;
}
