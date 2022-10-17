package com.example.filmoteca.controller;

import com.example.filmoteca.model.Director;
import com.example.filmoteca.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "filmoteca/v1/director")
public class DirectorController {
    @Autowired
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping(value = "/")
    public List<Director> getListDirector() {
        return directorService.getDirector();
    }

    @RequestMapping(value = "ajax/{letterDirector}")
    public List<Director> getDirectorAJAX(@PathVariable("letterDirector") String letterDirector) {
        return directorService.getDirectorAJAX(letterDirector);
    }

    @RequestMapping(value = "{directorID}")
    public Optional<Director> getDirectorById(@PathVariable("directorID") Long directorID) {
        return directorService.getDirectorById(directorID);
    }

    @PostMapping
    public void registerNewDirector(@RequestBody Director director) {
        directorService.addNewDirector(director);
    }

    @DeleteMapping(path = "{directorId}")
    public void deleteDirectorById(@PathVariable("directorId") Long directorId) {
        directorService.deleteDirectorById(directorId);
    }

    @PutMapping()
    public Director updateDirector(@RequestBody Director director) {
        return directorService.updateDirector(director);
    }

}
