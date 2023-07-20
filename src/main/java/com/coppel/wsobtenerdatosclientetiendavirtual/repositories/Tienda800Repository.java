
package com.coppel.wsobtenerdatosclientetiendavirtual.repositories;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DetalleNotaDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DetalleNotaCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.tda800.Tda800Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Tienda800Repository {
    @Autowired
    @Qualifier("jdbcTienda800")
    private NamedParameterJdbcTemplate tda800;

    public boolean actualizaInventarioEcomm(Tda800Model model) {

        String sqlQuery = "select fun_actualizarsurtidoropaautomaticowm(" +
                ":numbodega ::smallint," +
                ":totalUnidades ::smallint," +
                ":totalpesos ::integer," +
                ":olpnid ::varchar," +
                ":tienda ::smallint," +
                ":tipoguia ::smallint," +
                ":cantlotes ::smallint," +
                ":centro ::integer," +
                ":jabasgrandes ::smallint," +
                ":jabaschicas ::smallint," +
                ":consecutivo ::smallint," +
                ":lnomguia ::varchar[]," +
                ":lnumlote ::smallint[]," +
                ":lnumcodigo ::integer[]," +
                ":lnumtalla ::smallint[]," +
                ":lpreciounitario ::integer[]," +
                ":lcantidad ::smallint[]," +
                ":lpreciomaximo ::integer[]," +
                ":lmarca ::varchar[]," +
                ":lesimportacion ::varchar[]," +
                ":lareacodigo ::varchar[]," +
                ":lnumdepto ::smallint[]," +
                ":lnumclase ::smallint[]," +
                ":numfamilia ::smallint[]," +
                ":lesdescontinuado ::varchar[]," +
                ":lnumcentro ::integer[]," +
                ":lmanejaiva ::smallint[]," +
                ":limpprecioventa ::smallint[]," +
                ":lprecioventaminimo ::smallint[]," +
                ":lrebajado ::smallint[]," +
                ":lfechabaja ::varchar[]," +
                ":lidimportador ::integer[]," +
                ":ldescproveedor ::varchar[]," +
                ":ldesclarga ::varchar[] ," +
                ":lpreciodescontinuado ::smallint[]," +
                ":lnumempleado ::integer[]," +
                ":lnomcentro ::varchar[]);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("numbodega", model.getNumbodega());
        param.addValue("totalUnidades", model.getTotalUnidades());
        param.addValue("totalpesos", model.getTotalPesos());
        param.addValue("olpnid", model.getMaster_olpnid_guia());
        param.addValue("tienda", model.getTienda());
        param.addValue("tipoguia", model.getTipoguia());
        param.addValue("cantlotes", model.getCantlotes());
        param.addValue("centro", model.getCentro());
        param.addValue("jabasgrandes", model.getJabasGrandes());
        param.addValue("jabaschicas", model.getJabasChicas());
        param.addValue("consecutivo", model.getConsecutivo());
        param.addValue("lnomguia", model.getNomguia().toArray(new String[0]));
        param.addValue("lnumlote", model.getNumlote().toArray(new Integer[0]));
        param.addValue("lnumcodigo", model.getNumcodigo().toArray(new Integer[0]));
        param.addValue("lnumtalla", model.getNumtalla().toArray(new Integer[0]));
        param.addValue("lpreciounitario", model.getPreciounitario().toArray(new Integer[0]));
        param.addValue("lcantidad", model.getCantidad().toArray(new Integer[0]) );
        param.addValue("lpreciomaximo", model.getPreciomaximo().toArray(new Integer[0]));
        param.addValue("lmarca",model.getMarca().toArray(new String[0]));
        param.addValue("lesimportacion", model.getEsimportacion().toArray(new String[0]));
        param.addValue("lareacodigo", model.getAreacodigo().toArray(new String[0]));
        param.addValue("lnumdepto", model.getNumdepto().toArray(new Integer[0]));
        param.addValue("lnumclase", model.getNumclase().toArray(new Integer[0]));
        param.addValue("numfamilia", model.getNumfamilia().toArray(new Integer[0]));
        param.addValue("lesdescontinuado", model.getEsdescontinuado().toArray(new Integer[0]));
        param.addValue("lnumcentro", model.getNumcentro().toArray(new Integer[0]));
        param.addValue("lmanejaiva", model.getManejaiva().toArray(new Integer[0]));
        param.addValue("limpprecioventa", model.getImp_precioventa().toArray(new Integer[0]));
        param.addValue("lprecioventaminimo", model.getPrecioventaminimo().toArray(new Integer[0]));
        param.addValue("lrebajado", model.getRebajado().toArray(new Integer[0]));
        param.addValue("lfechabaja", model.getFechabaja().toArray(new String[0]));
        param.addValue("lidimportador", model.getId_importador().toArray(new Integer[0]));
        param.addValue("ldescproveedor", model.getDesc_proveedor().toArray(new String[0]));
        param.addValue("ldesclarga", model.getDesc_larga().toArray(new String[0]));
        param.addValue("lpreciodescontinuado", model.getPreciodescontinuado().toArray(new Integer[0]));
        param.addValue("lnumempleado", model.getNumempleado().toArray(new Integer[0]));
        param.addValue("lnomcentro", model.getNomcentro().toArray(new String[0]));

        tda800.queryForObject(sqlQuery, param, String.class);


        return true;
    }

    /* Notas Contado y Credito para la generacion de tickets */

    public List<DetalleNotaDTO> obtenerDetalleNota(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException {
        String sSql= "select " +
                "imp_nuevoropa, " +
                "imp_enganche, " +
                "prec_unitario, " +
                "nom_descripcion, " +
                "num_codigo, " +
                "num_talla, " +
                "num_sobreprecio, " +
                "num_cantidad, " +
                "pago_dineroe, " +
                "gano_dineroe, " +
                "descuento " +
                "FROM fun_consulta_detallefolionota(:folioRopa, :numCliente, :fechaFactura::date)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);
        param.addValue("numCliente", numCliente);
        param.addValue("fechaFactura", fechaFactura);
        List<DetalleNotaDTO> detalleNotaDTO = new ArrayList<>();

        try{
            detalleNotaDTO = tda800.query(sSql, param,new BeanPropertyRowMapper<>(DetalleNotaDTO.class));

        }catch (Exception w){
         System.out.println(w.getMessage());
        }

        return detalleNotaDTO;
    }

    public List<DetalleNotaCreditoDTO> obtenerDetalleNotaCredito(Integer folioRopa, Integer numCliente, String fechaFactura) throws SQLException {
        String sSql= "select " +
                "imp_nuevoropa, " +
                "imp_enganche, " +
                "prec_unitario, " +
                "nom_descripcion, " +
                "num_codigo, " +
                "num_talla, " +
                "num_sobreprecio, " +
                "num_cantidad, " +
                "num_descuento, " +
                "pago_dineroe, " +
                "gano_dineroe, " +
                "total_dineroe " +
                "FROM fun_consulta_detallefolionotacredito(:folioRopa, :numCliente, :fechaFactura::date);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);
        param.addValue("numCliente", numCliente);
        param.addValue("fechaFactura", fechaFactura);

        List<DetalleNotaCreditoDTO> detalleNotaCreditoDTO = new ArrayList<>();

        try{
            detalleNotaCreditoDTO = tda800.query(sSql, param,new BeanPropertyRowMapper<>(DetalleNotaCreditoDTO.class));

        }catch (Exception w){
            System.out.println(w.getMessage());
        }

        return detalleNotaCreditoDTO;

    }

}
