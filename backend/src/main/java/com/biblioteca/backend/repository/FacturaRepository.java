package com.biblioteca.backend.repository;
import com.biblioteca.backend.exception.DataBaseOperationException;
import com.biblioteca.backend.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FacturaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Factura> facturaRowMapper = (rs, rowNum) -> {
        Factura factura = new Factura();
        factura.setId(rs.getInt("id"));
        factura.setCliente(rs.getString("cliente"));
        factura.setFecha(rs.getObject("fecha", LocalDateTime.class));
        factura.setEstado(rs.getInt("estado"));
        return factura;
    };

    public int crearFactura(Factura factura) {
        try{
            return jdbcTemplate.queryForObject(
                    "EXEC crudFactura @cliente = ?, @fecha = ?, @opcion = 1",
                    new Object[]{factura.getCliente(), factura.getFecha()},
                    Integer.class
            );
        }catch (DataAccessException e){
            System.err.println("Error al crear factura: " + e.getMessage());
            throw new DataBaseOperationException("Error al crear la factura en la base de datos", e);
        }
    }

    public int actualizarFactura(Factura factura) {
        try{
            return jdbcTemplate.update(
                    "EXEC crudFactura @id = ?, @cliente = ?, @fecha = ?,  @opcion = 2",
                    factura.getId(), factura.getCliente(), factura.getFecha()
            );
        }catch (DataAccessException e){
            System.err.println("Error al actualizar factura con ID " + factura.getId() + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al actualizar la factura en la base de datos", e);
        }

    }

    public int eliminarFactura(int id) {
        try{
            return jdbcTemplate.update(
                    "EXEC crudFactura @id = ?, @opcion = 3",
                    id
            );
        }catch (DataAccessException e){
            System.err.println("Error al eliminar factura con ID " + id + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al eliminar la factura en la base de datos", e);
        }

    }

    public List<Factura> listarFacturas() {
        try{
            return jdbcTemplate.query(
                    "EXEC crudFactura @opcion = 4",
                    facturaRowMapper
            );
        }catch (DataBaseOperationException e){
            System.err.println("Error al listar facturas: " + e.getMessage());
            throw new DataBaseOperationException("Error al listar las facturas desde la base de datos", e);
        }

    }

    public Factura obtenerFacturaPorId(int id) {
        try{
            return jdbcTemplate.queryForObject(
                    "EXEC crudFactura @id = ?, @opcion = 5",
                    new Object[]{id},
                    facturaRowMapper
            );
        }catch (EmptyResultDataAccessException e){
            return null;
        }catch (DataBaseOperationException e){
            System.err.println("Error al obtener factura con ID " + id + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al obtener la factura desde la base de datos", e);
        }
    }
}