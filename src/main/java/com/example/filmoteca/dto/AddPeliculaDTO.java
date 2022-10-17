package com.example.filmoteca.dto;

public class AddPeliculaDTO {

    private Long id;

    private String emailusuario;

    private String nombreDirector;

    private String title;

    private String anio;

    private String comentario;

    private String nota;

    private Boolean vista;

    public AddPeliculaDTO() {

    }

    public AddPeliculaDTO(Long id, String emailusuario, String nombreDirector, String title, String anio,
                          String comentario, String nota, Boolean vista) {
        super();
        this.id = id;
        this.emailusuario = emailusuario;
        this.nombreDirector = nombreDirector;
        this.title = title;
        this.anio = anio;
        this.comentario = comentario;
        this.nota = nota;
        this.vista = vista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVista() {
        return vista;
    }

    public void setVista(Boolean vista) {
        this.vista = vista;
    }

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "AddPeliculaDTO [id=" + id + ", emailusuario=" + emailusuario + ", nombreDirector=" + nombreDirector
                + ", title=" + title + ", anio=" + anio + ", comentario=" + comentario + ", nota=" + nota + ", vista="
                + vista + "]";
    }

}
