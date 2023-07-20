package com.coppel.wsobtenerdatosclientetiendavirtual.repositories;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DatosClienteCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DatosClienteDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.DatosNotaDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.DetalleDevolucionDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.mappers.DatosClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository

public class TiendaVirtualRepository {

    @Autowired
    @Qualifier("jdbcTiendaVirtual")
    private NamedParameterJdbcTemplate tdaVirtual;

    private static final Logger log = LoggerFactory.getLogger(TiendaVirtualRepository.class);


    public com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO obtenerInformacionCliente(Integer factura, Integer cedis){
        String sSql= "select num_factura," +
                "num_tienda," +
                "idu_pedido," +
                "num_cliente," +
                "nombre," +
                "apellido_paterno," +
                "apellido_materno," +
                "nom_personarecibe," +
                "des_email,nom_calle," +
                "nom_entrecalles," +
                "num_casa," +
                "num_codigopostal," +
                "des_observaciones," +
                "num_interior," +
                "num_telefono," +
                "des_colonia," +
                "des_estado," +
                "des_ciudad," +
                "num_zona," +
                "num_ciudad," +
                "num_codigord," +
                "des_fechaventa," +
                "des_fechapromesa," +
                "des_tipo, " +
                "num_devolucion," +
                "flag_devolucion, " +
                "tipo_devolucion," +
                "det_devolucion " +
                "FROM fun_tvobtenerdatosclienteporfactura(:factura,:cedis);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("factura", factura);
        param.addValue("cedis", cedis);

        return tdaVirtual.queryForObject(sSql, param,new DatosClienteMapper() );

    }

    public DatosNotaDTO obtenerDatosNota(Integer folioRopa){
        String sSql= "select fecha_factura, " +
                "num_folio, " +
                "nom_email, " +
                "tipo_pago " +
                "FROM fun_consulta_datos_factura(:folioRopa)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);

        return tdaVirtual.queryForObject(sSql, param,new BeanPropertyRowMapper<>(DatosNotaDTO.class) );

    }

    public DatosClienteDTO obtenerDatosCliente(Integer folioRopa){
        String sSql= "select num_clientecoppel," +
                "nom_clientecoppel " +
                "FROM fun_consulta_nombre_clientecoppelporfolio(:folioRopa) limit 1;";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);

        return tdaVirtual.queryForObject(sSql, param,new BeanPropertyRowMapper<>(DatosClienteDTO.class) );

    }

    public DatosClienteCreditoDTO obtenerDatosClienteCredito(Integer folioRopa){
        String sSql= "select num_clientecredito," +
                "nom_clientecredito, " +
                "imp_nuevoabonoropa, " +
                "des_tipopago, " +
                "num_empleado " +
                "FROM fun_consulta_datos_clientecreditoporfolio(:folioRopa) limit 1;";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);

        return tdaVirtual.queryForObject(sSql, param,new BeanPropertyRowMapper<>(DatosClienteCreditoDTO.class) );

    }

    public String obtenerNombreArticulo(Integer codigo){
        String sSql= "select nom_articulo " +
                "FROM fun_consultanombrearticulooe(:codigo::integer);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("codigo", codigo);

        return tdaVirtual.queryForObject(sSql, param, String.class);

    }

    public  Integer  obtenerSaldoElectronico(Integer folioRopa){
        String sSql= "select " +
                "saldo_dineroe " +
                "FROM fun_consulta_saldo_dineroe(:folioRopa::integer);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);

        return tdaVirtual.queryForObject(sSql, param,Integer.class );

    }

    public List<DetalleDevolucionDTO> obtenerDetalleDevolucion(Integer folioRopa) {
        String sSql= "select num_codigo, " +
                "num_talla, " +
                "id_serial, " +
                "nom_motivo, " +
                "num_folio_devolucion, " +
                "fecha_devolucion " +
                "FROM fun_consultardetalle_devolucionropa(:folioRopa);";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("folioRopa", folioRopa);

        List<DetalleDevolucionDTO> detalleDevolucion = new ArrayList<>();

        try{
            detalleDevolucion = tdaVirtual.query(sSql, param,new BeanPropertyRowMapper<>(DetalleDevolucionDTO.class));

        }catch (Exception w){
            log.error(w.getMessage());
        }

        return detalleDevolucion;

    }

}
