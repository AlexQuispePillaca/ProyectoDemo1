package com.example.demoods.service;

import com.example.demoods.dto.ComentarioDTO;
import com.example.demoods.entity.Comentario;
import com.example.demoods.entity.Publicacion;
import com.example.demoods.exeption.ResourceNotFoundException;
import com.example.demoods.repository.IComentarioRepository;
import com.example.demoods.repository.IPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements IComentarioServicio{
    @Autowired
    private IComentarioRepository iComentarioRepository;
    @Autowired
    private IPublicacionRepository iPublicacionRepository;
    @Override
    public ComentarioDTO guardarComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario=convertirComentario(comentarioDTO);
        Publicacion publicacion=iPublicacionRepository.findById(publicacionId).orElseThrow(()->new ResourceNotFoundException("Publicación","id",publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario=iComentarioRepository.save(comentario);
        return convertirComentarioDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(long publicacionId) {
        List<Comentario> comentarioList = iComentarioRepository.findByPublicacionId(publicacionId);
        return comentarioList.stream().map(comentario -> convertirComentarioDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(long comentarioId) {
        Comentario comentario=iComentarioRepository.findById(comentarioId).orElseThrow(() ->new  ResourceNotFoundException("Comentario","id",comentarioId));
        return convertirComentarioDTO(comentario);
    }

    @Override
    public void eliminarComentarioPorId(long comentarioId) {
        Comentario comentario=iComentarioRepository.findById(comentarioId).orElseThrow(() ->new  ResourceNotFoundException("Comentario","id",comentarioId));
        iComentarioRepository.delete(comentario);

    }

    //Convertir a DTO
    private ComentarioDTO convertirComentarioDTO(Comentario comentario){
        ComentarioDTO comentarioDTO=new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        return comentarioDTO;

    }

    //Convertir a Entidad
    private Comentario convertirComentario(ComentarioDTO comentarioDTO){
        Comentario comentario=new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        return comentario;
    }
}
