package com.example.demoods.service;

import com.example.demoods.dto.PublicacionDTO;
import com.example.demoods.dto.PublicacionRespuesta;
import com.example.demoods.entity.Publicacion;
import com.example.demoods.exeption.ResourceNotFoundException;
import com.example.demoods.repository.IPublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements IPublicacionServicio {
    @Autowired
    private IPublicacionRepository iPublicacionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion=iPublicacionRepository.save(convertirEntidad(publicacionDTO));
        PublicacionDTO publicacionDTO1=convertirDTO(publicacion);
        return publicacionDTO1;


    }
    public PublicacionRespuesta listarPublicacion(int numeroDePagina, int medidaDePagina,String ordenarPor,String aod){
        Sort sort=aod.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable= PageRequest.of(numeroDePagina,medidaDePagina, sort);
        Page<Publicacion> publicaciones=iPublicacionRepository.findAll(pageable);
        List<Publicacion> listaDePublicaciones=publicaciones.getContent();
        List<PublicacionDTO> contenido=listaDePublicaciones.stream().map(publicacion -> convertirDTO(publicacion)).collect(Collectors.toList());
        PublicacionRespuesta publicacionRespuesta=new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroDePagina(publicaciones.getNumber());
        publicacionRespuesta.setMedidaDePagina(publicaciones.getSize());
        publicacionRespuesta.setTotalDeElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalDePaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());
        return publicacionRespuesta;

    }

    public PublicacionDTO publicacionId(long id){
        Publicacion publicacion=new Publicacion();
        publicacion=iPublicacionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",id));
        return convertirDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicaciona=new Publicacion();
        PublicacionDTO publicacionreturn=new PublicacionDTO();
        publicaciona=iPublicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicación","id",id));
        publicaciona.setTitulo(publicacionDTO.getTitulo());
        publicaciona.setDescripcion(publicacionDTO.getDescripcion());
        publicaciona.setContenido(publicacionDTO.getContenido());
        publicacionreturn=convertirDTO(iPublicacionRepository.save(publicaciona));
        return publicacionreturn;
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion=iPublicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id",id));
        iPublicacionRepository.delete(publicacion);

    }

    //convertir DTO a Entidad
    private Publicacion convertirEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion=modelMapper.map(publicacionDTO,Publicacion.class);
        return publicacion;
    }
    //convertir Entidad a DTO
    private PublicacionDTO convertirDTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO=modelMapper.map(publicacion,PublicacionDTO.class);
        return publicacionDTO;
    }
}
