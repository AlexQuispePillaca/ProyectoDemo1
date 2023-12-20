package com.example.demoods.dto;

import java.util.List;

public class PublicacionRespuesta {
    private List<PublicacionDTO> contenido;
    private int numeroDePagina;
    private int medidaDePagina;
    private long totalDeElementos;
    private int totalDePaginas;
    private boolean ultima;

    public List<PublicacionDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<PublicacionDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public int getMedidaDePagina() {
        return medidaDePagina;
    }

    public void setMedidaDePagina(int medidaDePagina) {
        this.medidaDePagina = medidaDePagina;
    }

    public long getTotalDeElementos() {
        return totalDeElementos;
    }

    public void setTotalDeElementos(long totalDeElementos) {
        this.totalDeElementos = totalDeElementos;
    }

    public int getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(int totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public PublicacionRespuesta() {
    }
}
