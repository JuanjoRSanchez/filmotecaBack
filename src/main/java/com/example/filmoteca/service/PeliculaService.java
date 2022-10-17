package com.example.filmoteca.service;

import com.example.filmoteca.dto.AddPeliculaDTO;
import com.example.filmoteca.model.Director;
import com.example.filmoteca.model.Pelicula;
import com.example.filmoteca.model.PeliculaUsuario;
import com.example.filmoteca.model.Usuario;
import com.example.filmoteca.repository.DirectorRepository;
import com.example.filmoteca.repository.PeliculaUsuarioRepository;
import com.example.filmoteca.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {


    @Autowired
    private final DirectorRepository directorRepository;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private final PeliculasRepository peliculasRepositories;

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    private final PeliculaUsuarioRepository pelis_ususRepositories;

    @Autowired
    public PeliculaService(DirectorRepository directorRepository, PeliculasRepository peliculasRepositories,
                           PeliculaUsuarioRepository pelis_ususRepositories) {
        this.directorRepository = directorRepository;
        this.peliculasRepositories = peliculasRepositories;
        this.pelis_ususRepositories = pelis_ususRepositories;
    }

    public int setPelicula(AddPeliculaDTO addpeliculadto) {
        /*
         * 0 pelicula guardada 1 pelicula ya guardada anteriormente 2 error
         */
        Pelicula pelicula = new Pelicula();
        PeliculaUsuario pelis_usus = new PeliculaUsuario();
        Usuario usuario = usuarioService.getUsuarioByMail(addpeliculadto.getEmailusuario());
        pelis_usus.setUsuario(usuario);
        Optional<Pelicula> peliculaOp = Optional
                .ofNullable(peliculasRepositories.findByTitle(addpeliculadto.getTitle()));
        if (!peliculaOp.isPresent()) {
            pelicula.setTitle(addpeliculadto.getTitle());
            pelicula.setAnio(addpeliculadto.getAnio());
            Optional<Director> directorOp = directorRepository.findDirectorByName(addpeliculadto.getNombreDirector());
            Director director1 = new Director();
            if (!directorOp.isPresent()) {
                director1.setName(addpeliculadto.getNombreDirector());
                directorRepository.save(director1);
                pelicula.setDirector(director1);
            } else {
                Director director01 = new Director();
                Director director = directorOp.orElse(director01);
                pelicula.setDirector(director);
            }
            pelicula.setTitle(addpeliculadto.getTitle());
            pelicula.setAnio(addpeliculadto.getAnio());
            peliculasRepositories.save(pelicula);
            pelis_usus.setPelicula(pelicula);
        } else {
            pelis_usus.setPelicula(peliculaOp.get());
            List<PeliculaUsuario> listaPeliUsu = pelis_ususRepositories.findByUsuario(usuario);
            if (listaPeliUsu != null) {
                for (PeliculaUsuario p : listaPeliUsu) {
                    if (p.getPelicula().equals(peliculaOp.get())) {
                        return 1;
                    }
                }
            }
        }
        pelis_usus.setComentario(addpeliculadto.getComentario());
        pelis_usus.setNota(addpeliculadto.getNota());
        pelis_usus.setUsuario(usuario);

        pelis_usus.setVista(addpeliculadto.getVista());
        pelis_ususRepositories.save(pelis_usus);
        return 0;
    }

    public AddPeliculaDTO getPeliculaUsusById(Long id) {
        PeliculaUsuario pelis_usus01 = new PeliculaUsuario();
        PeliculaUsuario pelis_usus = pelis_ususRepositories.findById(id).orElse(pelis_usus01);
        AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
        if (pelis_usus.getPelicula() != null) {
            Director director01 = new Director();
            Director director = directorRepository.findById(pelis_usus.getPelicula().getDirector().getId_director())
                    .orElse(director01);

            addPeliculaDTO.setId(pelis_usus.getId());
            addPeliculaDTO.setAnio(pelis_usus.getPelicula().getAnio());
            addPeliculaDTO.setComentario(pelis_usus.getComentario());
            addPeliculaDTO.setNombreDirector(director.getName());
            addPeliculaDTO.setNota(pelis_usus.getNota());
            addPeliculaDTO.setNombreDirector(pelis_usus.getPelicula().getDirector().getName());
            addPeliculaDTO.setTitle(pelis_usus.getPelicula().getTitle());
            addPeliculaDTO.setVista(pelis_usus.isVista());
            return addPeliculaDTO;
        }
        return addPeliculaDTO;
    }

    public String deletePeliculaById(Long id) {
        String result = "";
        PeliculaUsuario pelis_usus =  getPeliculaUsuById(id);
        if (pelis_usus != null) {
            pelis_ususRepositories.deleteById(id);
            result = "ok";
        }
        return result;
    }

    public List<AddPeliculaDTO> getPeliculasAddDTO(String emailUsuario) {
        List<AddPeliculaDTO> listaDTO = new ArrayList<>();

        Usuario usuario = usuarioService.getUsuarioByMail(emailUsuario);
        List<PeliculaUsuario> pOptional = pelis_ususRepositories.findByUsuario(usuario);
        if (!pOptional.isEmpty()) {
            for (PeliculaUsuario pelis_usus : pOptional) {
                Director director01 = new Director();
                Director director = directorService.getDirectorById(pelis_usus.getPelicula().getDirector().getId_director())
                        .orElse(director01);
                AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
                addPeliculaDTO.setId(pelis_usus.getId());
                addPeliculaDTO.setAnio(pelis_usus.getPelicula().getAnio());
                addPeliculaDTO.setComentario(pelis_usus.getComentario());
                addPeliculaDTO.setNombreDirector(director.getName());
                addPeliculaDTO.setNota(pelis_usus.getNota());
                addPeliculaDTO.setNombreDirector(pelis_usus.getPelicula().getDirector().getName());
                addPeliculaDTO.setTitle(pelis_usus.getPelicula().getTitle());
                addPeliculaDTO.setVista(pelis_usus.isVista());
                listaDTO.add(addPeliculaDTO);
            }
        }
        return listaDTO;
    }

    public PeliculaUsuario getPeliculaUsuById(Long y) {
        PeliculaUsuario pl = new PeliculaUsuario();
        return pelis_ususRepositories.findById(y).orElse(pl);
    }

    public List<Pelicula> getPeliculaTitulo(String titulo) {
        return peliculasRepositories.findByTitleStartingWith(titulo);
    }

    public List<String> getPeliculaTituloAJAX(String tituloPelicula) {
        List<String> resultList = new ArrayList<>();
        List<Pelicula> peliculas = peliculasRepositories.findByTitleStartingWith(tituloPelicula);
        for (Pelicula peli : peliculas) {
            resultList.add(peli.getTitle());
        }
        return resultList;
    }

    public String updatePeliculaUsuario(AddPeliculaDTO addPeliculaDTO, Long id) {
        String result = "ok";
        PeliculaUsuario pelis_usus01 = new PeliculaUsuario();
        PeliculaUsuario pelis_usus = pelis_ususRepositories.findById(id).orElse(pelis_usus01);
        pelis_usus.setComentario(addPeliculaDTO.getComentario());
        pelis_usus.setNota(addPeliculaDTO.getNota());
        pelis_usus.setVista(addPeliculaDTO.getVista());

        Pelicula pelicula = peliculasRepositories.findByTitle(addPeliculaDTO.getTitle());

        pelicula.setAnio(addPeliculaDTO.getAnio());
        pelicula.setTitle(addPeliculaDTO.getTitle());

        peliculasRepositories.save(pelicula);
        pelis_usus.setPelicula(pelicula);
        pelis_ususRepositories.save(pelis_usus);

        return result;
    }

    public Optional<Pelicula> getPeliculaByTitle(String title) {

        Optional<Pelicula> pelicula = Optional.of(peliculasRepositories.findByTitle(title));
        if (pelicula != null) {

            return pelicula;
        }

        return pelicula;
    }


    public List<PeliculaUsuario> getPeliculas(String emailUsuario) {
        Usuario usuario =  usuarioService.getUsuarioByMail(emailUsuario);
        return pelis_ususRepositories.findByUsuario(usuario);
    }


    public PeliculaUsuario getPeliculaUsuByTitle(Long y) {
        PeliculaUsuario pelis_usus01 = new PeliculaUsuario();
        PeliculaUsuario pelis_usus = pelis_ususRepositories.findById(y).orElse(pelis_usus01);
        return pelis_usus;
    }


    public List<Pelicula> getPelicula() {
        return peliculasRepositories.findAll();
    }

    public Pelicula getPeliculaById(Long id) {

        Optional<Pelicula> pelicula = peliculasRepositories.findById(id);
        if (!pelicula.isPresent()) {
            throw new IllegalStateException("La pelicula no existe en la base de datos");
        }

        return pelicula.get();
    }


    public Pelicula addNewPelicula(Pelicula pelicula) {
        Director director01 = new Director();
        Director director = directorRepository.findDirectorByName(pelicula.getDirector().getName()).orElse(director01);

        Optional<Pelicula> peliculaOptional = Optional
                .ofNullable(peliculasRepositories.findByTitle(pelicula.getTitle()));

        if (director != null) {
            pelicula.setDirector(director);

        }
        if (peliculaOptional.isPresent()) {

            throw new IllegalStateException("La pelicula ya existe en la base de datos");
        }
        return peliculasRepositories.save(pelicula);
    }

}
