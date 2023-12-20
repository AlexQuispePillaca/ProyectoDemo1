package com.example.demoods.service;

import com.example.demoods.dto.ComentarioDTO;

import java.util.List;

public interface IComentarioServicio {
    public ComentarioDTO guardarComentario(long publicacionId,ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(long publicacionId);
    public ComentarioDTO obtenerComentarioPorId(long comentarioId);
    public void eliminarComentarioPorId(long comentarioId);

}
