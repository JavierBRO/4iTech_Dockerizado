package com.iTech.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;
    @Column(columnDefinition = "boolean")
    private Boolean hasSockets;
    private String photoUrl;

//    // @OneToMany con Keynotes en iteraciones posteriores, asociación bidireccional
//    @OneToMany
//    @ToString.Exclude // para evitar errores con el fetching LAZY de objetos.
//    private List<Keynote> keynotes;
    @Column(columnDefinition = "boolean")
    private Boolean visible;
}
// {"id":2,"name":"ByteSphere Convergence Hall","capacity":350,"hasSockets":false,"photoUrl":"https://placehold.co/600x400.png","keynotes":[]}