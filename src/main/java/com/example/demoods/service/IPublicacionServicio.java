package com.example.demoods.service;

import com.example.demoods.dto.PublicacionDTO;
import com.example.demoods.dto.PublicacionRespuesta;




public interface IPublicacionServicio {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionRespuesta listarPublicacion(int numeroDePagina, int medidaDePagina,String ordenarPor,String aod);
    public PublicacionDTO publicacionId(long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
    public void eliminarPublicacion(long id);

}
