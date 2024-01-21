package com.example.demoods.service;

import com.example.demoods.dto.ComentarioDTO;
import com.example.demoods.entity.Comentario;
import com.example.demoods.entity.Publicacion;
import com.example.demoods.exeption.BlogAppException;
import com.example.demoods.exeption.ResourceNotFoundException;
import com.example.demoods.repository.IComentarioRepository;
import com.example.demoods.repository.IPublicacionRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ComentarioDTO guardarComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario=convertirComentario(comentarioDTO);
        Publicacion publicacion=iPublicacionRepository.findById(publicacionId).orElseThrow(()->new ResourceNotFoundException("Publicación","id",publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario=iComentarioRepository.save(comentario);
        return convertirComentarioDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(Long publicacionId) {
        List<Comentario> comentarioList = iComentarioRepository.findByPublicacionId(publicacionId);
        return comentarioList.stream().map(comentario -> convertirComentarioDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(Long publicacionId,Long comentarioId) {
        Publicacion publicacion=iPublicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));
        Comentario comentario=iComentarioRepository.findById(comentarioId)
                .orElseThrow(() ->new  ResourceNotFoundException("Comentario","id",comentarioId));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Comentario no encontrado");
        }

        return convertirComentarioDTO(comentario);

    }

    @Override
    public void eliminarComentarioPorId(Long publicacionId,Long comentarioId) {
        Publicacion publicacion=iPublicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));
        Comentario comentario=iComentarioRepository.findById(comentarioId)
                .orElseThrow(() ->new  ResourceNotFoundException("Comentario","id",comentarioId));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Comentario no encontrado");
        }
        iComentarioRepository.delete(comentario);

    }

    @Override
    public ComentarioDTO actualizarComentario(Long publicacionId, ComentarioDTO cuerpoComentarioDTO,Long comentarioId) {
        Publicacion publicacion=iPublicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));
        Comentario comentario=iComentarioRepository.findById(comentarioId)
                .orElseThrow(() ->new  ResourceNotFoundException("Comentario","id",comentarioId));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Comentario no encontrado");
        }
        comentario.setNombre(cuerpoComentarioDTO.getNombre());
        comentario.setEmail(cuerpoComentarioDTO.getEmail());
        comentario.setCuerpo(cuerpoComentarioDTO.getCuerpo());

        Comentario comentarioActualizado=iComentarioRepository.save(comentario);

        return convertirComentarioDTO(comentarioActualizado);
    }

    //Convertir a DTO
    private ComentarioDTO convertirComentarioDTO(Comentario comentario){
        ComentarioDTO comentarioDTO=modelMapper.map(comentario,ComentarioDTO.class);
        return comentarioDTO;

    }

    //Convertir a Entidad
    private Comentario convertirComentario(ComentarioDTO comentarioDTO){
        Comentario comentario=modelMapper.map(comentarioDTO,Comentario.class);
        return comentario;
    }
}
