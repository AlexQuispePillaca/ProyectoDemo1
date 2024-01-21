package com.example.demoods.service;

import com.example.demoods.dto.ComentarioDTO;

import java.util.List;

public interface IComentarioServicio {
    public ComentarioDTO guardarComentario(long publicacionId,ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(Long publicacionId);
    public ComentarioDTO obtenerComentarioPorId(Long publicacionId,Long comentarioId);
    public void eliminarComentarioPorId(Long publicacionId,Long comentarioId);
    public ComentarioDTO actualizarComentario(Long publicacionId,ComentarioDTO cuerpoComentarioDTO,Long comentarioId);

}
