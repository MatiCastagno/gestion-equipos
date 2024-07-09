package org.example.equipos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipo_seq")
    @SequenceGenerator(name = "equipo_seq", sequenceName = "equipo_sequence", allocationSize = 1)
    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
