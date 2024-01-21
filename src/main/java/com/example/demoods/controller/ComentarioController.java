package com.example.demoods.controller;

import com.example.demoods.dto.ComentarioDTO;
import com.example.demoods.entity.Comentario;
import com.example.demoods.service.IComentarioServicio;
import com.example.demoods.service.IPublicacionServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {
    @Autowired
    private IComentarioServicio iComentarioServicio;

    //Guardar Comentario con el Id de la publicación correspondiente
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(name = "publicacionId")long publicacionId,@RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(iComentarioServicio.guardarComentario(publicacionId,comentarioDTO), HttpStatus.CREATED);
    }
    //Obterner Comentario por Id de publicación
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> obtnerComentariosPorIdPublicacion(@PathVariable(name = "publicacionId")Long puvblicacionId){
        return iComentarioServicio.obtenerComentarioPorPublicacionId(puvblicacionId);
    }
    //Obtener comentario por Id de comentario
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(name = "publicacionId")Long publicacionId,@PathVariable(name = "comentarioId")Long comentarioId){
        ComentarioDTO comentarioDTO=iComentarioServicio.obtenerComentarioPorId(publicacionId,comentarioId);
        return new ResponseEntity<>(comentarioDTO,HttpStatus.OK);
    }
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(name = "publicacionId")Long publicacionId,@RequestBody ComentarioDTO cuerpoComentarioDTO,@PathVariable(name = "comentarioId")Long comentarioId){
        ComentarioDTO comentarioDTO=iComentarioServicio.actualizarComentario(publicacionId,cuerpoComentarioDTO,comentarioId);
        return new ResponseEntity<>(comentarioDTO,HttpStatus.OK);
    }
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(name = "publicacionId")Long publicacionId,@PathVariable(name = "comentarioId")Long comentarioId){
        iComentarioServicio.eliminarComentarioPorId(publicacionId,comentarioId);
        return new ResponseEntity<>("comentario elominado",HttpStatus.OK);
    }


}
