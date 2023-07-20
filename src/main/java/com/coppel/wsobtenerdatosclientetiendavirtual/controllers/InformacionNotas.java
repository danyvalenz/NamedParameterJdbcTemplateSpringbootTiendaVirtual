package com.coppel.wsobtenerdatosclientetiendavirtual.controllers;

import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Contado.DetalleNotaDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.Credito.DetalleNotaCreditoDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.DetalleDevolucionDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.SendMailApiParams;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.NotasDTO.SendMailApiRequestDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.services.Tienda800Service;
import com.coppel.wsobtenerdatosclientetiendavirtual.services.TiendaVirtualService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class InformacionNotas {

    @Autowired
    private TiendaVirtualService tiendaVirtualService;

    @Autowired
    private Tienda800Service tienda800Service;


    //Procesa la nota y devuelve el cuerpo para hacer el envio del correo
    @GetMapping ("InformacionNotas/ProcesarNota")
    public SendMailApiRequestDTO procesaNota(@RequestParam int folioRopa) throws SQLException, JSONException {
        SendMailApiRequestDTO  responseDTO = new SendMailApiRequestDTO();

        //Datos de la Nota
        Integer folio_ropa = folioRopa;
        Integer numFolio = tiendaVirtualService.obtenerDatosNota(folioRopa).getNum_folio();
        Integer tipoPago = tiendaVirtualService.obtenerDatosNota(folioRopa).getTipo_pago();
        String email = tiendaVirtualService.obtenerDatosNota(folioRopa).getNom_email();
        String fechaFactura = tiendaVirtualService.obtenerDatosNota(folioRopa).getFecha_factura();

        //Obtener Saldo Electronico
        Integer totalDineroE = tiendaVirtualService.obtenerSaldoElectronico(folioRopa);

        if (tipoPago == 2){
            //Nota de Contado
            responseDTO.setOptions(procesarNotaContado(folioRopa, tipoPago, fechaFactura, numFolio, totalDineroE));
        } else {
            //Nota de Credito
            responseDTO.setOptions(procesarNotaCredito(folioRopa, tipoPago, fechaFactura, numFolio, totalDineroE));

        }
        responseDTO.setTipoPago(tipoPago);
        responseDTO.setEmail(email);

        return responseDTO;
    }

    /**********************************************************************************************************
     *****                                                                                                *****
     *****                                        Notas Contado                                           *****
     *****                                                                                                *****
     **********************************************************************************************************/

    //Procesa la nota de contado
    public List<SendMailApiParams> procesarNotaContado(Integer folioRopa, Integer tipoPago, String fechaFactura, Integer numFolio, Integer dineroE) throws SQLException, JSONException {
        List<SendMailApiParams> sendMailApiParamsList = new ArrayList<>();

        Integer importeTotal = 0;
        Integer pago_dineroe = 0;
        Integer gano_dineroe = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hora = dtf.format(now);
        //Datos Cliente
        String nombreCliente = tiendaVirtualService.obtenerDatosCliente(folioRopa).getNom_clienteCoppel();
        Integer numeroCliente = tiendaVirtualService.obtenerDatosCliente(folioRopa).getNum_clienteCoppel();

        //Detalle Nota
        List<DetalleNotaDTO> nota = (List<DetalleNotaDTO>) tienda800Service.obtenerDetalleNota(folioRopa,numeroCliente,fechaFactura);
        for (DetalleNotaDTO detalleNota: nota) {
            importeTotal += detalleNota.getPrec_unitario();
            pago_dineroe = detalleNota.getPago_dineroe();
            gano_dineroe = detalleNota.getGano_dineroe();
        }
        //Detalle Devolucion
        List<DetalleDevolucionDTO> detalleDevolucion = (List<DetalleDevolucionDTO>) tiendaVirtualService.obtenerDetalleDevolucion(folioRopa);

        sendMailApiParamsList.add(new SendMailApiParams("folio", folioRopa));
        sendMailApiParamsList.add(new SendMailApiParams("fecha_entrega", fechaFactura));
        sendMailApiParamsList.add(new SendMailApiParams("nombre_completo", nombreCliente));
        sendMailApiParamsList.add(new SendMailApiParams("num_cliente", numeroCliente));
        sendMailApiParamsList.add(new SendMailApiParams("num_orden", numFolio));
        sendMailApiParamsList.add(new SendMailApiParams("html_productos", String.valueOf(obtenerHtmlArticulo(nota))));
        sendMailApiParamsList.add(new SendMailApiParams("html_codigo", String.valueOf(obtenerHtmlDevolucion(detalleDevolucion, nota, tipoPago))));
        sendMailApiParamsList.add(new SendMailApiParams("num_importe", importeTotal));
        sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo1", pago_dineroe));
        sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo2", gano_dineroe));
        sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo3", dineroE));
        sendMailApiParamsList.add(new SendMailApiParams("cant_articulo", nota.size()));
        sendMailApiParamsList.add(new SendMailApiParams("hora_concierto", hora));

        return sendMailApiParamsList;
    }

    //Obtiene la descripcion del articulo de contado
    public String obtenerDescripcionArticulo(Integer numCodigo, List<DetalleNotaDTO> detalleNota){
        String desArticulo = null;

        for (DetalleNotaDTO detalleNotas: detalleNota) {
            if (numCodigo.equals(detalleNotas.getNum_codigo())){
                desArticulo = detalleNotas.getNom_descripcion();
            }
        }
        return desArticulo;
    }

    /**********************************************************************************************************
     *****                                                                                                *****
     *****                                        Notas Credito                                           *****
     *****                                                                                                *****
     **********************************************************************************************************/

    //Procesa la nota de credito
    public List<SendMailApiParams> procesarNotaCredito(Integer folioRopa, Integer tipoPago, String fechaFactura, Integer numFolio, Integer dineroE) throws SQLException {
        List<SendMailApiParams> sendMailApiParamsList = new ArrayList<>();

        Integer importeTotal = 0;
        Integer pago_dineroe = 0;
        Integer gano_dineroe = 0;
        Integer saldonuevoropa = 0;
        Integer enganche=0;
        Integer total_precio_credito=0;
        Integer descuentoempleado=0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hora = dtf.format(now);

        //Datos Cliente
        String nombreCliente = tiendaVirtualService.obtenerDatosClienteCredito(folioRopa).getNom_clienteCredito();
        Integer numeroCliente = tiendaVirtualService.obtenerDatosClienteCredito(folioRopa).getNum_clienteCredito();
        Integer imp_nuevoabonoropa = tiendaVirtualService.obtenerDatosClienteCredito(folioRopa).getImp_nuevoabonoropa();
        String des_tipopago = tiendaVirtualService.obtenerDatosClienteCredito(folioRopa).getDes_tipopago();
        Integer num_empleado = tiendaVirtualService.obtenerDatosClienteCredito(folioRopa).getNum_empleado();

        if (imp_nuevoabonoropa.equals(null)){
            imp_nuevoabonoropa = 0;
        }
        //Detalle Nota
        List<DetalleNotaCreditoDTO> nota = (List<DetalleNotaCreditoDTO>) tienda800Service.obtenerDetalleNotaCredito(folioRopa,numeroCliente,fechaFactura);
        //Detalle Devolucion
        List<DetalleDevolucionDTO> detalleDevolucion = (List<DetalleDevolucionDTO>) tiendaVirtualService.obtenerDetalleDevolucion(folioRopa);

        for (DetalleNotaCreditoDTO detalleNota: nota) {
            importeTotal += detalleNota.getPrec_unitario();
            pago_dineroe = detalleNota.getPago_dineroe();
            gano_dineroe = detalleNota.getGano_dineroe();

            if (detalleNota.getNum_sobreprecio() >= detalleNota.getPrec_unitario()){
                total_precio_credito += detalleNota.getNum_sobreprecio();
            } else {
                if (num_empleado != 0) {
                    total_precio_credito += detalleNota.getPrec_unitario() + detalleNota.getNum_sobreprecio();
                }
                total_precio_credito += detalleNota.getPrec_unitario();
            }

            enganche = detalleNota.getImp_enganche();
            saldonuevoropa = detalleNota.getImp_nuevoropa();
            descuentoempleado += detalleNota.getDescuento();

        }

        sendMailApiParamsList.add(new SendMailApiParams("folio", folioRopa));
        sendMailApiParamsList.add(new SendMailApiParams("fecha_entrega", fechaFactura));
        sendMailApiParamsList.add(new SendMailApiParams("nombre_completo", nombreCliente));
        sendMailApiParamsList.add(new SendMailApiParams("num_cliente", numeroCliente));
        sendMailApiParamsList.add(new SendMailApiParams("num_orden", numFolio));
        sendMailApiParamsList.add(new SendMailApiParams("html_productos", String.valueOf(obtenerHtmlArticuloCredito(nota))));
        sendMailApiParamsList.add(new SendMailApiParams("html_codigo", String.valueOf(obtenerHtmlDevolucion(detalleDevolucion, nota, tipoPago))));
        sendMailApiParamsList.add(new SendMailApiParams("num_empleado", num_empleado));

        if (num_empleado == 0){
            sendMailApiParamsList.add(new SendMailApiParams("total_credito", total_precio_credito));
            sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo1", pago_dineroe));
            sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo2", gano_dineroe));
            sendMailApiParamsList.add(new SendMailApiParams("idu_ArticuloCodigo3", dineroE));
            sendMailApiParamsList.add(new SendMailApiParams("total_abono", enganche));
            sendMailApiParamsList.add(new SendMailApiParams("num_importe", total_precio_credito - enganche - pago_dineroe));
            sendMailApiParamsList.add(new SendMailApiParams("total_contado", saldonuevoropa - (total_precio_credito - enganche - pago_dineroe)));
            sendMailApiParamsList.add(new SendMailApiParams("saldo", saldonuevoropa));
            sendMailApiParamsList.add(new SendMailApiParams("saldo_cancelado", Integer.toString(imp_nuevoabonoropa) + ".00"));

        }else {
            sendMailApiParamsList.add(new SendMailApiParams("total_credito", total_precio_credito));
            sendMailApiParamsList.add(new SendMailApiParams("des_adicional", descuentoempleado));
            sendMailApiParamsList.add(new SendMailApiParams("total_a_pagar", total_precio_credito - descuentoempleado));
            sendMailApiParamsList.add(new SendMailApiParams("importe_de", total_precio_credito - descuentoempleado));
        }
        sendMailApiParamsList.add(new SendMailApiParams("cant_articulo", nota.size()));
        sendMailApiParamsList.add(new SendMailApiParams("hora_concierto", hora));

        return sendMailApiParamsList;

    }

    //Obtiene la descripcion del articulo de credito
    public String obtenerDescripcionArticuloCredito(Integer numCodigo, List<DetalleNotaCreditoDTO> detalleNota){
        String desArticulo = null;

        for (DetalleNotaCreditoDTO detalleNotas: detalleNota) {
            if (numCodigo.equals(detalleNotas.getNum_codigo())){
                desArticulo = detalleNotas.getNom_descripcion();
            }
        }
        return desArticulo;
    }

    /**********************************************************************************************************
     *****                                                                                                *****
     *****                                             HTML                                               *****
     *****                                                                                                *****
     **********************************************************************************************************/

    //Obtiene el cuerpo html del detalle de los articulos de contado
    public StringBuilder obtenerHtmlArticulo(List<DetalleNotaDTO> detalleNotaDTOS){
        StringBuilder html = new StringBuilder();
        List<DetalleNotaDTO> detalleNota = detalleNotaDTOS;
        html.append("<table cellpadding='0' cellspacing='0' align='left' width='100%' border='0' style='font-size:12px; font-family:Arial, Helvetica, sans-serif'>");

        for (DetalleNotaDTO detalleNotaDTO : detalleNota) {
            String nombreArticulo = tiendaVirtualService.obtenerNombreArticulo(detalleNotaDTO.getNum_codigo());
            html.append("<tr>");
            html.append("<td align='left' width='18%'>").append(detalleNotaDTO.getNum_codigo()).append("</td>");
            html.append("<td align='left' width='32%'>").append(nombreArticulo).append("</td>");
            html.append("<td align='center' width='10%'>").append(detalleNotaDTO.getNum_cantidad()).append("</td>");
            html.append("<td align='center' width='9%'>").append(detalleNotaDTO.getNum_talla()).append("</td>");
            html.append("<td align='right' width='18%'>").append(detalleNotaDTO.getPrec_unitario()).append(".00 </td>");
            html.append("</tr>");

            if (detalleNotaDTO.getDescuento() > 0){
                html.append("<tr>");
                html.append("<td align='left'> </td>");
                html.append("<td align='center'> </td>");
                html.append("<td align='center'> </td>");
                html.append("<td align='center'> </td>");
                html.append("<td align='left'>Descuento del").append(detalleNotaDTO.getDescuento()/detalleNotaDTO.getPrec_unitario()).append("</td>");
                html.append("<td align='right'>").append(detalleNotaDTO.getDescuento()).append(".00 </td>");
                html.append("</tr>");

            }
        }
        html.append("</table>");

        return html;
    }

    //Obtiene el cuerpo html del detalle de los articulos de credito

    public StringBuilder obtenerHtmlArticuloCredito(List<DetalleNotaCreditoDTO> detalleNotaCreditoDTOS){
        StringBuilder html = new StringBuilder();
        List<DetalleNotaCreditoDTO> detalleNotaCreditoDTO = detalleNotaCreditoDTOS;
        html.append("<table cellpadding='0' cellspacing='0' align='left' width='100%' border='0' style='font-size:12px; font-family:Arial, Helvetica, sans-serif'>");

        for (DetalleNotaCreditoDTO detalleNotaDTO : detalleNotaCreditoDTO) {
            String nombreArticulo = tiendaVirtualService.obtenerNombreArticulo(detalleNotaDTO.getNum_codigo());
            html.append("<tr>");
            html.append("<td align='left' width='32%'>").append(detalleNotaDTO.getNum_codigo()).append("</td>");
            html.append("<td align='left' width='18%'>").append(nombreArticulo).append("</td>");
            html.append("<td align='center' width='9%'>").append(detalleNotaDTO.getNum_talla()).append("</td>");
            html.append("<td align='center' width='10%'>").append(detalleNotaDTO.getNum_cantidad()).append("</td>");
            html.append("<td align='right' width='18%'>").append(detalleNotaDTO.getPrec_unitario()).append(".00 </td>");
            html.append("/<tr>");

        }
        html.append("</table>");

        return html;
    }

    //Obtiene el cuerpo html del detalle devolucion
    public StringBuilder obtenerHtmlDevolucion(List<DetalleDevolucionDTO> detalleDevolucionDTO, List detalleNota, Integer tipoPago){
        StringBuilder html = new StringBuilder();
        List<DetalleDevolucionDTO> detalleDevolucion = detalleDevolucionDTO;
        html.append("<table cellpadding='0' cellspacing='0' align='left' width='100%' border='0' style='font-size:12px; font-family:Arial, Helvetica, sans-serif'>");

        for (DetalleDevolucionDTO detalleDevolucionDTOS : detalleDevolucion) {
            int codigo = detalleDevolucionDTOS.getNum_codigo();
            String talla = detalleDevolucionDTOS.getNum_talla();

            html.append("<tr><td align='center' style='font-size:6px' colspan='2'><hr color='#000000'/></td></tr>");
            html.append("<tr><td align='left' colspan='2'><b>Folio DevoluciÃ³n: </b>").append(detalleDevolucionDTOS.getNum_folio_devolucion()).append("</td>");
            html.append("<tr><td align='left' colspan='2'><b>Devolucion</b></td></tr>");
            html.append("<tr><td align='left' colspan='2'>").append(detalleDevolucionDTOS.getFecha_devolucion()).append("</td></tr>");
            html.append("<tr><td align='left' colspan='2'>").append(detalleDevolucionDTOS.getNom_motivo()).append("</td></tr>");

            html.append("<tr><td align='left' colspan='2'><b>&nbsp;</b></td></tr>");

            html.append("<tr>");
            html.append("<td>");
            html.append("<table cellpadding='0' cellspacing='0' align='left' width='100%' border='0' style='font-size:12px; font-family:Arial, Helvetica, sans-serif'>");

            html.append("<tr>");
            html.append("<td align='left' width='18%'>C&oacute;digo</td>");
            html.append("<td align='left' width='10%'>Cant</td>");
            html.append("<td align='left' width='9%'>T</td>");
            html.append("<td align='left' width='32%'>Descripci&oacute;n</td>");
            html.append("</tr>");

            html.append("<tr>");
            html.append("<td align='left'>").append(codigo).append("</td>");
            html.append("<td align='left'>1</td>");
            html.append("<td align='left'>").append(talla).append("</td>");
            if (tipoPago == 2){
                String descripcion = obtenerDescripcionArticulo(detalleDevolucionDTOS.getNum_codigo(),detalleNota);
                html.append("<td align='left'>").append(descripcion).append("</td>");

            } else {
                String descripcion = obtenerDescripcionArticuloCredito(detalleDevolucionDTOS.getNum_codigo(),detalleNota);
                html.append("<td align='left'>").append(descripcion).append("</td>");

            }
            html.append("</tr>");

            html.append("</table>");
            html.append("</td>");
            html.append("</tr>");

        }
        html.append("<tr><td align='center' style='font-size:6px' colspan='2'><hr color='#000000'/></td></tr>");
        html.append("<tr><td height='10px' colspan='2'>&nbsp;</td></tr>");

        return html;
    }
}
