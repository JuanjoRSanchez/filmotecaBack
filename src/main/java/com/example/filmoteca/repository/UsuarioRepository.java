package com.example.filmoteca.repository;

import com.example.filmoteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByEmail(String email);

    Optional<Usuario> findByPassword(String password);
}
