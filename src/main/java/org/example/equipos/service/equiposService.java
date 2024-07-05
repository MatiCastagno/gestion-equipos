package org.example.equipos.service;

import org.example.equipos.model.Equipo;
import org.example.equipos.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class equiposService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> getEquipoById(Long id) {
        return equipoRepository.findById(id);
    }

    public List<Equipo> buscarEquiposByNombre(String nombre) {
        return equipoRepository.findByNombreContaining(nombre);
    }

    public Equipo crearEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Optional<Equipo> modicarEquipo(Long id, Equipo equipoDetails) {
        return equipoRepository.findById(id).map(equipo -> {
            equipo.setNombre(equipoDetails.getNombre());
            equipo.setLiga(equipoDetails.getLiga());
            equipo.setPais(equipoDetails.getPais());
            return equipoRepository.save(equipo);
        });
    }

    public boolean deleteEquipo(Long id) {
        return equipoRepository.findById(id).map(equipo -> {
            equipoRepository.delete(equipo);
            return true;
        }).orElse(false);
    }
}
