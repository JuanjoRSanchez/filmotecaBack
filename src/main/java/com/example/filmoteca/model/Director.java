package com.example.filmoteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_director;

    @Column
    private String name;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Pelicula> peliculas = new HashSet<>();

    public Director() {

    }

    public Director(Long id_director, String name, Set<Pelicula> peliculas) {
        this.id_director = id_director;
        this.name = name;
        this.peliculas = peliculas;
    }

    public Long getId_director() {
        return id_director;
    }

    public void setId_director(Long id_director) {
        this.id_director = id_director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Director [id_director=" + id_director + ", name=" + name + ", peliculas=" + peliculas + "]";
    }

}
