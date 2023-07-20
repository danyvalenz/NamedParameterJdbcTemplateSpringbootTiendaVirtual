package com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseDataDTO{
    private Integer folio; //NumFolioRopa
    private String fecha_entrega; //fechaFactura
    private String nombre_completo; //NombreCliente
    private Integer num_cliente; //NumeroCliente
    private Integer num_orden; //NumFolio
    private String html_productos; //HtmlDetalleProductos
    private String html_codigo; //HtmlDetalleDevolucion
    private Integer num_importe; //ImporteTotal
    private Integer idu_ArticuloCodigo1; //PagoDineroElectronico
    private Integer idu_ArticuloCodigo2; //GanoDineroElectronico
    private Integer idu_ArticuloCodigo3; //TotalDineroElectronico
    private Integer cant_articulo; //CantidadArticulo
    private String hora_concierto; //HoraActual

}
