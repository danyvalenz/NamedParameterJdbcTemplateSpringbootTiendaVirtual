package com.coppel.wsobtenerdatosclientetiendavirtual.util;

public class STATUS {
    public static int Status;
    public static String Mensaje;

    static {
        Status = 1;
        Mensaje = "";
    }

    public static void OK(String mensaje){
        Status = 1;
        Mensaje = mensaje;
    }
    public static void Error(String mensaje) {
        Status = -1;
        Mensaje = mensaje;
    }

}
