package org.example.equipos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.equipos.model.Equipo;
import org.example.equipos.service.EquipoService;
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
    private EquipoService equipoService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontraron equipos")
    })
    public ResponseEntity<?> getAllEquipos() {

        try {
            return new ResponseEntity<>(equipoService.getAllEquipos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Equipos no encontrados", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontro el equipo")
    })
    public ResponseEntity<?> getEquipoById(@PathVariable Long id) {
        Optional<?> equipo = equipoService.getEquipoById(id);

        if (equipo.isEmpty()){

            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Obtener equipo por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontro el equipo")
    })
    public List<Equipo> searchEquiposByNombre(@RequestParam String nombre) {
        return equipoService.buscarEquiposByNombre(nombre);
    }

    @PostMapping
    @Operation(summary = "Crear Equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se pudo crear correctamente el equipo")
    })
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        return new ResponseEntity<Equipo>(equipoService.crearEquipo(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontro equipo")
    })
    public ResponseEntity<?> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        Optional<Equipo> updatedEquipo = equipoService.modicarEquipo(id, equipoDetails);

        if (updatedEquipo.isEmpty()){

            return new ResponseEntity<>(updatedEquipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontro el equipo")
    })
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        boolean deleted = false;
        String error= "";
        try {
            deleted = equipoService.deleteEquipo(id);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
