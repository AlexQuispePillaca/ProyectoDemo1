package com.example.demoods.repository;

import com.example.demoods.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacionRepository extends JpaRepository<Publicacion,Long> {
}
