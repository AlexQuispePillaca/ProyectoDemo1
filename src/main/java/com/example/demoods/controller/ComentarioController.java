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

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(name = "publicacionId")long publicacionId,@RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(iComentarioServicio.guardarComentario(publicacionId,comentarioDTO), HttpStatus.CREATED);


    }
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> obtnerPublicacionPorId(@PathVariable(name = "publicacionId")long puvblicacionId){
        return iComentarioServicio.obtenerComentarioPorPublicacionId(puvblicacionId);
    }

    @GetMapping("/publicaciones/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(name = "comentarioId")long comentarioId){
        return ResponseEntity.ok(iComentarioServicio.obtenerComentarioPorId(comentarioId));
    }


}
