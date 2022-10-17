package com.example.filmoteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pelicula;

    @Column(unique = true)
    private String title;

    @Column(name = "anio")
    private String anio;

    @JsonIgnore
    @OneToMany(mappedBy = "pelicula", orphanRemoval = true)
    private List<PeliculaUsuario> peliculaUsuario = new ArrayList<PeliculaUsuario>();

    @ManyToOne()
    @JoinColumn(name = "id_director")
    private Director director;

    public Pelicula() {
    }

    public Pelicula(Long id_pelicula, String title, String anio, List<PeliculaUsuario> peliculaUsuario, Director director) {
        this.id_pelicula = id_pelicula;
        this.title = title;
        this.anio = anio;
        this.peliculaUsuario = peliculaUsuario;
        this.director = director;
    }

    public Long getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Long id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<PeliculaUsuario> getpeliculaUsuario() {
        return peliculaUsuario;
    }

    public void setpeliculaUsuario(List<PeliculaUsuario> peliculaUsuario) {
        this.peliculaUsuario = peliculaUsuario;
    }

}
