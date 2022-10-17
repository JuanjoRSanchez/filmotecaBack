package com.example.filmoteca.controller;


import com.example.filmoteca.dto.AddPeliculaDTO;
import com.example.filmoteca.model.Pelicula;
import com.example.filmoteca.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "filmoteca/v1/pelicula")
public class PeliculaController {

    @Autowired
    private final PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = "/listas/{emailUsuario}")
    public List<AddPeliculaDTO> getListPeliculasUsuarios(@PathVariable("emailUsuario") String emailUsuario) {
        return peliculaService.getPeliculasAddDTO(emailUsuario);
    }

    @GetMapping(value = "/{idPelicula}")
    public AddPeliculaDTO getListPeliculasUsuarioById(@PathVariable("idPelicula") Long idPelicula) {

        return peliculaService.getPeliculaUsusById(idPelicula);
    }

    @RequestMapping(value = "titulo/{tituloPelicula}")
    public List<AddPeliculaDTO> getPeliculabyTitleAJAX(@PathVariable("tituloPelicula") String tituloPelicula) {
        List<AddPeliculaDTO> listaDTO = new ArrayList<AddPeliculaDTO>();
        List<Pelicula> pelicula = new ArrayList<Pelicula>();
        pelicula = peliculaService.getPeliculaTitulo(tituloPelicula);
        for (Pelicula p : pelicula) {
            AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
            addPeliculaDTO.setAnio(p.getAnio());
            addPeliculaDTO.setTitle(p.getTitle());
            addPeliculaDTO.setNombreDirector(p.getDirector().getName());
            listaDTO.add(addPeliculaDTO);
        }

        return listaDTO;
    }

    @RequestMapping(value = "/peli/{tituloPelicula}")
    public List<String> getPeliculaAJAX(@PathVariable("tituloPelicula") String tituloPelicula) {
        List<String> resultList = new ArrayList<String>();
        resultList = peliculaService.getPeliculaTituloAJAX(tituloPelicula);

        return resultList;
    }

    @PutMapping(value = "/update/{id}")
    public String updatePeliculaUsus(@PathVariable Long id, @RequestBody AddPeliculaDTO addPeliculaDTO) {
        return peliculaService.updatePeliculaUsuario(addPeliculaDTO, id);
    }

    @PostMapping(value = "/add")
    public int setPelicula(@RequestBody AddPeliculaDTO addpeliculadto) {
        return peliculaService.setPelicula(addpeliculadto);
    }

    @DeleteMapping(value = "/borrar/{id}")
    public String deletePeliculaById(@PathVariable("id") Long id) {
        return peliculaService.deletePeliculaById(id);
    }

}
