package com.example.filmoteca.repository;

import com.example.filmoteca.model.Pelicula;
import com.example.filmoteca.model.PeliculaUsuario;
import com.example.filmoteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaUsuarioRepository extends JpaRepository<PeliculaUsuario, Long> {

    List<PeliculaUsuario> findByUsuario(Usuario usuario);

    PeliculaUsuario findByPelicula(Pelicula pelicula);
}
