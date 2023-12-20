package com.example.demoods.controller;

import com.example.demoods.dto.PublicacionDTO;
import com.example.demoods.dto.PublicacionRespuesta;
import com.example.demoods.service.IPublicacionServicio;
import com.example.demoods.utils.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {
    @Autowired
    private IPublicacionServicio iPublicacionServicio;

    @GetMapping
    public PublicacionRespuesta listarPublicacion(@RequestParam(value = "pageNo",defaultValue = AppConstantes.NUMER_DE_PAGINA_POR_DEFECTO,required = false) int nPage,
                                                  @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaPagina,
                                                  @RequestParam(value = "sortBy",defaultValue = AppConstantes.ORDENAR_POR_DEFECTO,required = false)String ordenarPor,
                                                  @RequestParam(value = "sortDir",defaultValue = AppConstantes.ORDENAR_DE_MANERA,required = false)String aod){
        return iPublicacionServicio.listarPublicacion(nPage,medidaPagina,ordenarPor,aod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> publicacionId(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(iPublicacionServicio.publicacionId(id));
    }
    @PostMapping
    public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(iPublicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(iPublicacionServicio.actualizarPublicacion(publicacionDTO,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        iPublicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación con el id : "+id+" eliminada",HttpStatus.OK);
    }
}
