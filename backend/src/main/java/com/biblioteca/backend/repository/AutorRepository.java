package com.biblioteca.backend.repository;

import com.biblioteca.backend.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class AutorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Autor> listarAutores() {
        return jdbcTemplate.query(
                "EXEC crudAutor @opcion = 4",
                (rs, rowNum) -> {
                    Autor autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNombre(rs.getString("nombre"));
                    autor.setEstado(rs.getInt("estado"));
                    return autor;
                }
        );
    }

    public Autor obtenerAutorPorId(int id) {
        return jdbcTemplate.queryForObject(
                "EXEC crudAutor @id = ?, @opcion = 5",
                new Object[]{id},
                (rs, rowNum) -> {
                    Autor autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNombre(rs.getString("nombre"));
                    autor.setEstado(rs.getInt("estado"));
                    return autor;
                }
                );
    }
    public Autor guardarAutor(Autor autor) {
        // Asumimos que el procedimiento almacenado 'crudAutor' manejará la inserción
        // y devolverá el ID del nuevo autor (si es generado por la base de datos).
        // Tendremos que averiguar los parámetros exactos que espera 'crudAutor' para la creación.

        // **ESTO ES UN EJEMPLO Y PUEDE NECESITAR AJUSTES SEGÚN CÓMO ESTÉ DEFINIDO 'crudAutor'**
        jdbcTemplate.update(
                "EXEC crudAutor @opcion = 1, @nombre = ?, @estado = ?",
                autor.getNombre(),
                autor.getEstado()
        );

        // Para simplificar, devolvemos el mismo objeto autor que se envió.
        // En una implementación real, podrías querer consultar el autor recién creado
        // para obtener el ID generado por la base de datos.
        return autor;
    }
    public Autor modificarAutor(Autor autor) {
        // Asumimos que el procedimiento almacenado 'crudAutor' manejará la actualización
        // utilizando el ID del autor. Necesitamos averiguar los parámetros exactos que espera.

        // **ESTO ES UN EJEMPLO Y PUEDE NECESITAR AJUSTES SEGÚN CÓMO ESTÉ DEFINIDO 'crudAutor'**
        int rowsAffected = jdbcTemplate.update(
                "EXEC crudAutor @opcion = 2, @id = ?, @nombre = ?",
                autor.getId(),
                autor.getNombre()
                // Podría haber otros campos a actualizar, como el estado
        );

        if (rowsAffected > 0) {
            return autor; // Indicamos que la actualización fue exitosa
        } else {
            return null; // Indicamos que no se encontró el autor con ese ID
        }
    }
    public boolean eliminarAutorLogico(int id) {
        // Asumimos que el procedimiento almacenado 'crudAutor' manejará la eliminación lógica
        // estableciendo el estado a 0 para el ID proporcionado.
        // Necesitamos averiguar los parámetros exactos que espera.

        // **ESTO ES UN EJEMPLO Y PUEDE NECESITAR AJUSTES SEGÚN CÓMO ESTÉ DEFINIDO 'crudAutor'**
        int rowsAffected = jdbcTemplate.update(
                "EXEC crudAutor @opcion = 3, @id = ?, @estado = 0",
                id
        );
        return rowsAffected > 0;
    }
}
