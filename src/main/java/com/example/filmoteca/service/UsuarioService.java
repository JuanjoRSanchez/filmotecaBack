package com.example.filmoteca.service;

import com.example.filmoteca.dto.LoginDTO;
import com.example.filmoteca.model.Rol;
import com.example.filmoteca.model.Usuario;
import com.example.filmoteca.repository.RolRepository;
import com.example.filmoteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepo;

    @Autowired
    private final RolRepository rolRepositories;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepo, RolRepository rolRepositories) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepositories = rolRepositories;
    }

    public Usuario getUsuarioByMail(String mail) {
        Usuario usuario01 = new Usuario();
        Usuario usuario = usuarioRepo.findUsuarioByEmail(mail).orElse(usuario01);

        return usuario;

    }

    public List<Usuario> getUsuario() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long usuarioId) {

        Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);

        return usuario;

    }

    public LoginDTO getUsuarioLog(String email, String password) {
        LoginDTO loginDTO = new LoginDTO();
        Usuario usuario = new Usuario();
        Optional<Usuario> usuario01 = usuarioRepo.findUsuarioByEmail(email);
        if (usuario01.isPresent()) {
            usuario = usuario01.get();
            String mail = usuario.getEmail();
            String pass = usuario.getPassword();
            if (email.equalsIgnoreCase(mail)) {
                if (password.equalsIgnoreCase(pass)) {
                    loginDTO.setValor(2);
                    loginDTO.setName(usuario.getName());
                    return loginDTO;
                }
                loginDTO.setValor(3);
                loginDTO.setName("El password no es correcto");
                return loginDTO;
            }
        } else {
            loginDTO.setValor(1);
            loginDTO.setName("No existe un usuario con este email");
            return loginDTO;
        }

        return loginDTO;

    }

    public Optional<Usuario> getUsuarioByIdSeguro(Long usuarioId) {

        Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);

        return usuario;

    }

    public int addNewUsuario(Usuario usuario) {
        int salida = 1;
        Optional<Usuario> usuarioOptional = usuarioRepo.findUsuarioByEmail(usuario.getEmail());

        if (usuarioOptional.isPresent()) {
            salida = 0;
        } else {
            if (usuario.getRoles().isEmpty()) {
                Rol rol = new Rol();
                rol.setId(1L);
                rol.setNombre("usuario");
                usuario.getRoles().add(rol);
            }
            usuarioRepo.save(usuario);
        }

        return salida;
    }

    public void deleteUsuario(Long usuarioId) {
        boolean exists = usuarioRepo.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("El usuario con Id " + usuarioId + " no existe.");
        }

        usuarioRepo.deleteById(usuarioId);
    }

    public Usuario updateUsuario(Usuario usuario) {

        Usuario usuarioBd = usuarioRepo.findById(usuario.getId_usuario())
                .orElseThrow(() -> new IllegalStateException(
                        "Usuario con id " + usuario.getId_usuario() + " no existe en la base de datos"));
        if (usuario.getName() != null && usuario.getName().length() > 0
                && !usuarioBd.getName().equals(usuario.getName())) {
            usuarioBd.setName(usuario.getName());
        }
        if (usuario.getPassword() != null && usuario.getPassword().length() > 0
                && !usuarioBd.getPassword().equals(usuario.getPassword())) {
            usuarioBd.setPassword(usuario.getPassword());
        }
        if (usuario.getEmail() != null && usuario.getEmail().length() > 0
                && !usuarioBd.getEmail().equals(usuario.getEmail())) {
            usuarioBd.setEmail(usuario.getEmail());
        }

        return usuarioRepo.save(usuarioBd);

    }

    public void addRoleToUser(String usuarioMail, Long rolId) {
        Usuario usuario01 = new Usuario();
        Usuario usuario = usuarioRepo.findUsuarioByEmail(usuarioMail).orElse(usuario01);
        Rol rol01 = new Rol();
        Rol rol = rolRepositories.findById(rolId).orElse(rol01);
        usuario.getRoles().add(rol);
    }


}
