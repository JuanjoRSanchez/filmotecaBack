package com.example.filmoteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "peliculaUsuario")
public class PeliculaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId", referencedColumnName = "id_usuario", updatable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicualId", referencedColumnName = "id_pelicula", updatable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Pelicula pelicula;

    private String nota;

    private String comentario;

    private boolean vista;

    public PeliculaUsuario(Long id, Usuario usuario, Pelicula pelicula, String nota, String comentario, boolean vista) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.nota = nota;
        this.comentario = comentario;
        this.vista = vista;
    }

    public PeliculaUsuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

}
