package com.example.filmoteca.controller;

import com.example.filmoteca.dto.LoginDTO;
import com.example.filmoteca.model.Usuario;
import com.example.filmoteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "filmoteca/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/")
    public List<Usuario> getListUsuario() {
        return usuarioService.getUsuario();
    }

    @RequestMapping(value = "{usuarioId}")
    public Optional<Usuario> getUsusarioById(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId);
    }

    @RequestMapping(value = "email/{emailUsuario}")
    public Usuario getUsusarioByEmail(@PathVariable("emailUsuario") String emailUsuario) {
        return usuarioService.getUsuarioByMail(emailUsuario);
    }

    @RequestMapping(value = "/login", params = { "email", "password" })
    public LoginDTO getUsusarioLogin(@RequestParam String email, String password) {
        return usuarioService.getUsuarioLog(email, password);
    }


    @PostMapping
    public int registerNewUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addNewUsuario(usuario);
    }

    @DeleteMapping(value = "{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }
}
