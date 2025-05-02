package com.biblioteca.backend.service;

import com.biblioteca.backend.model.Autor;
import com.biblioteca.backend.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAllAutores() {
        return autorRepository.listarAutores();
    }

    public Autor getAutorById(int id) {
        return autorRepository.obtenerAutorPorId(id);
    }
    public Autor crearAutor(Autor autor) {
        return autorRepository.guardarAutor(autor);
    }
    public Autor actualizarAutor(int id, Autor autor) {
        autor.setId(id);
        return autorRepository.modificarAutor(autor);
    }
    public boolean eliminarAutor(int id) {
        return autorRepository.eliminarAutorLogico(id);
    }
}
