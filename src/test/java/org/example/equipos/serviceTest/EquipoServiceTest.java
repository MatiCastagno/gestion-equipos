package org.example.equipos.serviceTest;

import org.example.equipos.model.Equipo;
import org.example.equipos.repository.EquipoRepository;
import org.example.equipos.service.EquipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceTest {

    @Mock
    EquipoRepository equipoRepository;

    @InjectMocks
    EquipoService equipoService = new EquipoService();

    private List<Equipo> equipos=null;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Equipo equipo1 = new Equipo(1L, "Juventus FC", "Serie A", "Italia");
        Equipo equipo2 = new Equipo(2L, "FC Barcelona", "La Liga", "España");
        Equipo equipo3 = new Equipo(3L, "Real Madrid", "La Liga", "España");
        Equipo equipo4 = new Equipo(4L, "Galatasaray SK", "Süper Lig", "Turquía");
        Equipo equipo5 = new Equipo(5L, "Ajax Amsterdam", "Eredivisie", "Países Bajos");

        equipos = Arrays.asList(equipo1, equipo2, equipo3, equipo4, equipo5);
    }

    @Test
    void getAllEquipos() {

        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> result = equipoService.getAllEquipos();
        assertEquals(5, result.size());
        assertEquals(equipos.get(0), result.get(0));
        assertEquals(equipos.get(1), result.get(1));
    }

    @Test
    void getEquipoById() {

        when(equipoRepository.findById(4L)).thenReturn(Optional.of(equipos.get(3)));

        Optional<Equipo> result = equipoService.getEquipoById(4L);
        assertTrue(result.isPresent());
        assertEquals(equipos.get(3), result.get());
    }

    @Test
    void buscarEquiposByNombre() {

        when(equipoRepository.findByNombreContaining("Real")).thenReturn(Arrays.asList(equipos.get(2)));

        List<Equipo> result = equipoService.buscarEquiposByNombre("Real");
        assertEquals(1, result.size());
        assertEquals(equipos.get(2), result.get(0));
    }

    @Test
    void crearEquipo() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "La Liga", "España");
        when(equipoRepository.save(equipo)).thenReturn(equipo);

        Equipo result = equipoService.crearEquipo(equipo);
        assertEquals(equipo, result);
    }

    @Test
    void modificarEquipo() {
        Equipo equipoModificado = new Equipo(1L, "Real Madrid CF", "La Liga", "España");

        when(equipoRepository.findById(3L)).thenReturn(Optional.of(equipos.get(2)));
        when(equipoRepository.save(equipos.get(2))).thenReturn(equipos.get(2));

        Optional<Equipo> result = equipoService.modicarEquipo(3L, equipoModificado);
        assertTrue(result.isPresent());
        assertEquals(equipoModificado.getNombre(), result.get().getNombre());
    }

    @Test
    void deleteEquipo() {

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipos.get(0)));

        boolean result = equipoService.deleteEquipo(1L);
        assertTrue(result);
    }
}
