package com.biblioteca.backend.repository;
import com.biblioteca.backend.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.queryForObject(
                "EXEC crudFactura @cliente = ?, @fecha = ?, @opcion = 1",
                new Object[]{factura.getCliente(), factura.getFecha()},
                Integer.class
        );
    }

    public int actualizarFactura(Factura factura) {
        return jdbcTemplate.update(
                "EXEC crudFactura @id = ?, @cliente = ?, @fecha = ?, @estado = ?, @opcion = 2",
                factura.getId(), factura.getCliente(), factura.getFecha(), factura.getEstado()
        );
    }

    public int eliminarFactura(int id) {
        return jdbcTemplate.update(
                "EXEC crudFactura @id = ?, @opcion = 3",
                id
        );
    }

    public List<Factura> listarFacturas() {
        return jdbcTemplate.query(
                "EXEC crudFactura @opcion = 4",
                facturaRowMapper
        );
    }

    public Factura obtenerFacturaPorId(int id) {
        return jdbcTemplate.queryForObject(
                "EXEC crudFactura @id = ?, @opcion = 5",
                new Object[]{id},
                facturaRowMapper
        );
    }
}