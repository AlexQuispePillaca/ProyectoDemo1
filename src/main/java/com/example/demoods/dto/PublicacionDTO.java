package com.example.demoods.dto;

import com.example.demoods.entity.Comentario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PublicacionDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2,message = "titulo muy corto")
    private String titulo;
    @NotEmpty
    @Size(min = 5,message = "descripcion muy corta")
    private String descripcion;

    private String contenido;

    private Set<Comentario> comentarios;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }
    public PublicacionDTO() {
    }
}
