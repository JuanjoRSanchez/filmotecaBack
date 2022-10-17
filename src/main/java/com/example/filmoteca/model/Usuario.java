package com.example.filmoteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta = new Date();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PeliculaUsuario> peliculaUsuario = new ArrayList<PeliculaUsuario>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id_usuario, String name, String password, String email, Date fechaAlta,
                   List<PeliculaUsuario> peliculaUsuario, Collection<Rol> roles) {
        super();
        this.id_usuario = id_usuario;
        this.name = name;
        this.password = password;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.peliculaUsuario = peliculaUsuario;
        this.roles = roles;
    }

    public List<PeliculaUsuario> getpeliculaUsuario() {
        return peliculaUsuario;
    }

    public void setpeliculaUsuario(List<PeliculaUsuario> pelis_usus) {
        this.peliculaUsuario = pelis_usus;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, fechaAlta, id_usuario, name, password, peliculaUsuario, roles);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(email, other.email) && Objects.equals(fechaAlta, other.fechaAlta)
                && Objects.equals(id_usuario, other.id_usuario) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password) && Objects.equals(peliculaUsuario, other.peliculaUsuario)
                && Objects.equals(roles, other.roles);
    }

}
