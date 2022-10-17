package com.example.filmoteca.repository;

import com.example.filmoteca.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findDirectorByName(String name);

    List<Director> findByNameStartingWith(String name);
}
