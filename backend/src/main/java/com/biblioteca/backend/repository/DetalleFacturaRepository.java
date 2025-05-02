package com.biblioteca.backend.repository;

import com.biblioteca.backend.model.DetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleFacturaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<DetalleFactura> detalleFacturaRowMapper = (rs, rowNum) -> {
        DetalleFactura detalle = new DetalleFactura();
        detalle.setId(rs.getInt("id"));
        detalle.setIdFactura(rs.getInt("idFactura"));
        detalle.setProducto(rs.getString("producto"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setPrecioUnitario(rs.getBigDecimal("precioUnitario"));
        detalle.setEstado(rs.getInt("estado"));
        return detalle;
    };

    public int crearDetalleFactura(DetalleFactura detalleFactura) {
        return jdbcTemplate.queryForObject(
                "EXEC crudDetalleFactura @idFactura = ?, @producto = ?, @cantidad = ?, @precioUnitario = ?, @opcion = 1",
                new Object[]{detalleFactura.getIdFactura(), detalleFactura.getProducto(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario()},
                Integer.class
        );
    }

    public int actualizarDetalleFactura(int id, DetalleFactura detalleFactura) {
        return jdbcTemplate.update(
                "EXEC crudDetalleFactura @id = ?, @idFactura = ?, @producto = ?, @cantidad = ?, @precioUnitario = ?, @opcion = 2",
                id, detalleFactura.getIdFactura(), detalleFactura.getProducto(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario()
        );
    }

    public int eliminarDetalleFactura(int id) {
        return jdbcTemplate.update(
                "EXEC crudDetalleFactura @id = ?, @opcion = 3",
                id
        );
    }

    public List<DetalleFactura> listarDetallesPorFactura(int facturaId) {
        return jdbcTemplate.query(
                "EXEC crudDetalleFactura @idFactura = ?, @opcion = 4",
                new Object[]{facturaId},
                detalleFacturaRowMapper
        );
    }

    public DetalleFactura obtenerDetalleFacturaPorId(int id) {
        return jdbcTemplate.queryForObject(
                "EXEC crudDetalleFactura @id = ?, @opcion = 5",
                new Object[]{id},
                detalleFacturaRowMapper
        );
    }
    public List<DetalleFactura> listarTodosDetallesFactura() {
        return jdbcTemplate.query(
                "EXEC crudDetalleFactura @opcion = 6",
                detalleFacturaRowMapper
        );
    }

}
