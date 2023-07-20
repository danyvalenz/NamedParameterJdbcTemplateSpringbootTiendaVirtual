package com.coppel.wsobtenerdatosclientetiendavirtual.dto;

import com.coppel.wsobtenerdatosclientetiendavirtual.util.Meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponseDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class ApiResponseDTO {

    private Meta meta;
    private Object data;

}
