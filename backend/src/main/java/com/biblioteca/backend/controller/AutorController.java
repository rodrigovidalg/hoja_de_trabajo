package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.Autor;
import com.biblioteca.backend.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listarAutores() {
        return autorService.getAllAutores();
    }

    @GetMapping("/{id}")
    public Autor obtenerAutorPorId(@PathVariable int id) {
        return autorService.getAutorById(id);
        }
    @PostMapping("/crear")
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
        Autor nuevoAutor = autorService.crearAutor(autor);
        return new ResponseEntity<>(nuevoAutor, HttpStatus.CREATED);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable int id, @RequestBody Autor autor) {
        Autor autorActualizado = autorService.actualizarAutor(id, autor);
        if (autorActualizado != null) {
            return new ResponseEntity<>(autorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable int id) {
        boolean eliminado = autorService.eliminarAutor(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si la eliminación lógica fue exitosa
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si no se encontró el autor
        }
    }
}
