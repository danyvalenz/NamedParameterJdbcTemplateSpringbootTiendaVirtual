package com.coppel.wsobtenerdatosclientetiendavirtual.services.impl;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DetalleNotaDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DetalleNotaCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.repositories.Tienda800Repository;
import com.coppel.wsobtenerdatosclientetiendavirtual.services.Tienda800Service;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800.DetalleRopa;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800.Tda800Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Tienda800ServiceImpl implements Tienda800Service {

    //CONEXION TIENDA 800
    @Autowired
    Tienda800Repository tda800Repository;

    @Override
    public boolean actualizaInventarioEcomm(Integer tienda, List<DetalleRopa> ldr) {
        Tda800Model t8m = crearObjetoTienda800(tienda,ldr);

        try{
            tda800Repository.actualizaInventarioEcomm(t8m);
        }catch(EmptyResultDataAccessException emtpy){
            return  false;
        }
        catch (Exception e){
            throw e;
        }
      return true;
    }

    private Tda800Model crearObjetoTienda800(Integer iNumTienda, List<DetalleRopa> ldr) {
        Tda800Model tda800Model = new Tda800Model();
        List<String>  nomguia = new ArrayList<>();
        List<Integer> numlote = new ArrayList<>();
        List<Integer> numcodigo = new ArrayList<>();
        List<Integer> numtalla = new ArrayList<>();
        List<Integer> preciounitario = new ArrayList<>();
        List<Integer> cantidad = new ArrayList<>();
        List<Integer> preciomaximo = new ArrayList<>();
        List<String> marca = new ArrayList<>();
        List<String> esimportacion = new ArrayList<>();
        List<String> areacodigo = new ArrayList<>();
        List<Integer> numdepto = new ArrayList<>();
        List<Integer> numclase = new ArrayList<>();
        List<Integer> numfamilia = new ArrayList<>();
        List<Integer> esdescontinuado = new ArrayList<>();
        List<Integer> numcentro = new ArrayList<>();
        List<Integer> manejaiva = new ArrayList<>();
        List<Integer> precioventaminimo = new ArrayList<>();
        List<Integer> rebajado = new ArrayList<>();
        List<Integer> preciodescontinuado = new ArrayList<>();
        List<Integer> numempleado = new ArrayList<>();
        List<String> nomcentro = new ArrayList<>();
        List<Integer> impPrecioventa = new ArrayList<>();
        List<String> fechabaja = new ArrayList<>();
        List<Integer> idImportador = new ArrayList<>();
        List<String> descProveedor = new ArrayList<>();
        List<String> descLarga = new ArrayList<>();

        for(DetalleRopa dr : ldr){
            nomguia.add(dr.getNomguia());
            numlote.add(dr.getNumlote());
            numcodigo.add(dr.getNumcodigo());
            numtalla.add(dr.getNumtalla());
            preciounitario.add(dr.getPreciounitario());
            cantidad.add(dr.getCantidad());
            preciomaximo.add(dr.getPreciomaximo());
            marca.add(dr.getMarca());
            esimportacion.add(dr.getEsimportacion());
            areacodigo.add(dr.getAreacodigo());
            numdepto.add(dr.getNumdepto());
            numclase.add(dr.getNumclase());
            numfamilia.add(dr.getNumfamilia());
            esdescontinuado.add(dr.getEsdescontinuado());
            numcentro.add(dr.getNumcentro());
            manejaiva.add(dr.getManejaiva());
            precioventaminimo.add(dr.getPrecioventaminimo());
            rebajado.add(dr.getRebajado());
            preciodescontinuado.add(dr.getPreciodescontinuado());
            numempleado.add(dr.getNumempleado());
            nomcentro.add(dr.getNomcentro());
            impPrecioventa.add(dr.getImp_precioventa());
            fechabaja.add(dr.getFechabaja());
            idImportador.add(dr.getId_importador());
            descProveedor.add(dr.getDesc_proveedor());
            descLarga.add(dr.getDesc_larga());
        }

        tda800Model.setNumbodega(ldr.get(0).getNumbodega());
        tda800Model.setTotalUnidades(ldr.get(0).getTotalunidades());
        tda800Model.setTotalPesos(ldr.get(0).getTotalpesos());
        tda800Model.setMaster_olpnid_guia(ldr.get(0).getNomguia());
        tda800Model.setTienda(iNumTienda);
        tda800Model.setTipoguia(1);
        tda800Model.setCantlotes(ldr.get(0).getCantlotes());
        tda800Model.setCentro(ldr.get(0).getNumcentro());
        tda800Model.setJabasGrandes(0);
        tda800Model.setJabasChicas(0);
        tda800Model.setConsecutivo(0);



        tda800Model.setNomguia(nomguia);
        tda800Model.setNumlote(numlote);
        tda800Model.setNumcodigo(numcodigo);
        tda800Model.setNumtalla(numtalla);
        tda800Model.setPreciounitario(preciounitario);
        tda800Model.setCantidad(cantidad);
        tda800Model.setPreciomaximo(preciomaximo);
        tda800Model.setMarca(marca);
        tda800Model.setEsimportacion(esimportacion);
        tda800Model.setAreacodigo(areacodigo);
        tda800Model.setNumdepto(numdepto);
        tda800Model.setNumclase(numclase);
        tda800Model.setNumfamilia(numfamilia);
        tda800Model.setEsdescontinuado(esdescontinuado);
        tda800Model.setNumcentro(numcentro);
        tda800Model.setManejaiva(manejaiva);
        tda800Model.setPrecioventaminimo(precioventaminimo);
        tda800Model.setRebajado(rebajado);
        tda800Model.setPreciodescontinuado(preciodescontinuado);
        tda800Model.setNumempleado(numempleado);
        tda800Model.setNomcentro(nomcentro);
        tda800Model.setImp_precioventa(impPrecioventa);
        tda800Model.setFechabaja(fechabaja);
        tda800Model.setId_importador(idImportador);
        tda800Model.setDesc_proveedor(descProveedor);
        tda800Model.setDesc_larga(descLarga);

        return tda800Model;

    }

    /* Notas Contado y Credito para la generacion de tickets */

    @Override
    public Object obtenerDetalleNota(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException {
        Object detalleNotaDTO;
        detalleNotaDTO = tda800Repository.obtenerDetalleNota(folioRopa, numCliente, fechaFactura);
        return detalleNotaDTO;
    }

    @Override
    public Object obtenerDetalleNotaCredito(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException {
        Object detalleNotaCredito;
        detalleNotaCredito = tda800Repository.obtenerDetalleNotaCredito(folioRopa, numCliente, fechaFactura);
        return detalleNotaCredito;
    }

}
