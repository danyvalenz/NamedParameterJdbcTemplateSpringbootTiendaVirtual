/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coppel.wsobtenerdatosclientetiendavirtual.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author daniel.valenzuela
 */
public class CustomException extends Exception {
    private String  message = "";
    private Exception e ;
    private Object obj;
    private String folioBitacora = "";
    private String json  = "";
    private String query ="";
    private String metodo ="";
    
    public CustomException(String message, Exception e , Object obj){
        this.e = e;
        this.message = message;
        this.obj = obj;
    }

    public CustomException(String message, Exception e, Object obj, String folioBitacora,  String query, String metodo) {
        this.message = message;
        this.e = e;
        this.obj = obj;
        this.folioBitacora = folioBitacora;
        try {
            this.json =  new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            this.json = "Error al parsear Json.";
        }
        this.query = query;
        this.metodo = metodo;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getFolioBitacora() {
        return folioBitacora;
    }

    public void setFolioBitacora(String folioBitacora) {
        this.folioBitacora = folioBitacora;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
}
