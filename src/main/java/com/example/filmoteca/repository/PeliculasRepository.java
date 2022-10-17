package com.example.filmoteca.repository;

import com.example.filmoteca.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Long> {

    Pelicula findByTitle(String title);

    List<Pelicula> findByTitleStartingWith(String titulo);
}
