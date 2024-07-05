package org.example.equipos.repository;

import org.example.equipos.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByNombreContaining(String nombre);
}
