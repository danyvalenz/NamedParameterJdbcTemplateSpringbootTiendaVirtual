package com.coppel.wsobtenerdatosclientetiendavirtual.util.mappers;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO;

import com.coppel.wsobtenerdatosclientetiendavirtual.util.devoluciones_texcoco.DevolucionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class DatosClienteMapper implements RowMapper<DatosClienteDTO> {

    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */

    @Override
    public DatosClienteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        DatosClienteDTO dto =  new DatosClienteDTO(
                rs.getInt("num_factura"),
                rs.getInt("num_tienda"),
                rs.getInt("idu_pedido"),
                rs.getInt("num_cliente"),
                rs.getString("nombre"),
                rs.getString("apellido_paterno"),
                rs.getString("apellido_materno"),
                rs.getString("nom_personarecibe"),
                rs.getString("des_email"),
                rs.getString("nom_calle"),
                rs.getString("nom_entrecalles"),
                rs.getInt("num_casa"),
                rs.getString("num_codigopostal"),
                rs.getString("des_observaciones"),
                rs.getString("num_interior"),
                rs.getString("num_telefono"),
                rs.getString("des_colonia"),
                rs.getString("des_estado"),
                rs.getString("des_ciudad"),
                rs.getInt("num_zona"),
                rs.getInt("num_ciudad"),
                rs.getInt("num_codigord"),
                rs.getString("des_fechapromesa"),
                rs.getString("des_fechaVenta"),
                rs.getString("des_tipo"),
                rs.getInt("num_devolucion"),
                rs.getBoolean("flag_devolucion"),
                rs.getInt("tipo_devolucion"),
                new ArrayList<>()
        );
        if (Objects.equals(rs.getString("det_devolucion").trim(), "")) {
            return dto;
        }
        try{
            dto.setDet_devolucion(new ObjectMapper().readValue(rs.getString("det_devolucion"), DevolucionWrapper.class).getDevolucionWrapper());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return dto;
    }
}
