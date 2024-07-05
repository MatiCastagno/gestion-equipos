package org.example.equipos.controller;

import org.example.equipos.model.Equipo;
import org.example.equipos.service.equiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/equipos")
public class equiposController {
    @Autowired
    private equiposService equipoService;

    @GetMapping("")
    public List<Equipo> getAllEquipos() {
        return equipoService.getAllEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipoById(@PathVariable Long id) {
        Optional<?> equipo = equipoService.getEquipoById(id);

        if (equipo.isEmpty()){

            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Equipo no encontrado", 404), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public List<Equipo> searchEquiposByNombre(@RequestParam String nombre) {
        return equipoService.buscarEquiposByNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        return new ResponseEntity<Equipo>(equipoService.crearEquipo(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        Optional<Equipo> updatedEquipo = equipoService.modicarEquipo(id, equipoDetails);

        if (updatedEquipo.isEmpty()){

            return new ResponseEntity<>(updatedEquipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Equipo no encontrado", 404), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        boolean deleted = equipoService.deleteEquipo(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(new ErrorResponse("Equipo no encontrado", 404), HttpStatus.NOT_FOUND);
    }

    static class ErrorResponse {
        private String mensaje;
        private int codigo;

        public ErrorResponse(String mensaje, int codigo) {
            this.mensaje = mensaje;
            this.codigo = codigo;
        }
    }
}
