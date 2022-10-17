package com.example.filmoteca.service;

import com.example.filmoteca.model.Rol;
import com.example.filmoteca.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {


    @Autowired
    RolRepository rolRepositories;

    public Optional<Rol> getByRolNombre(String rolNombre) {
        return rolRepositories.findByNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepositories.save(rol);
    }
}
