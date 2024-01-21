package com.example.demoods.dto;

import java.util.Date;

public class ErrorDetalles {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalle;

    public Date getMarcaDeTiempo() {
        return marcaDeTiempo;
    }

    public void setMarcaDeTiempo(Date marcaDeTiempo) {
        this.marcaDeTiempo = marcaDeTiempo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalle) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }
    public ErrorDetalles() {

    }
}
