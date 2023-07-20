package com.coppel.wsobtenerdatosclientetiendavirtual.util;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.DatosClienteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosClienteMapper  implements RowMapper<DatosClienteDTO> {

    @Override
    public DatosClienteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new DatosClienteDTO();
    }
}
