package com.biblioteca.backend.repository;

import com.biblioteca.backend.exception.DataBaseOperationException;
import com.biblioteca.backend.model.DetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            return jdbcTemplate.queryForObject(
                    "EXEC crudDetalleFactura @idFactura = ?, @producto = ?, @cantidad = ?, @precioUnitario = ?, @opcion = 1",
                    new Object[]{detalleFactura.getIdFactura(), detalleFactura.getProducto(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario()},
                    Integer.class
            );
        } catch (DataAccessException e) {
            System.err.println("Error al crear detalle de factura: " + e.getMessage());
            throw new DataBaseOperationException("Error al crear el detalle de factura en la base de datos", e);
        }
    }

    public int actualizarDetalleFactura(int id, DetalleFactura detalleFactura) {
        try {
            return jdbcTemplate.update(
                    "EXEC crudDetalleFactura @id = ?, @idFactura = ?, @producto = ?, @cantidad = ?, @precioUnitario = ?, @opcion = 2",
                    id, detalleFactura.getIdFactura(), detalleFactura.getProducto(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario()
            );
        } catch (DataAccessException e) {
            System.err.println("Error al actualizar detalle de factura con ID " + id + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al actualizar el detalle de factura en la base de datos", e);
        }
    }

    public int eliminarDetalleFactura(int id) {
        try {
            return jdbcTemplate.update(
                    "EXEC crudDetalleFactura @id = ?, @opcion = 3",
                    id
            );
        } catch (DataAccessException e) {
            System.err.println("Error al eliminar detalle de factura con ID " + id + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al eliminar el detalle de factura en la base de datos", e);
        }
    }

    public List<DetalleFactura> listarDetallesPorFactura(int facturaId) {
        try {
            return jdbcTemplate.query(
                    "EXEC crudDetalleFactura @idFactura = ?, @opcion = 4",
                    new Object[]{facturaId},
                    detalleFacturaRowMapper
            );
        } catch (DataAccessException e) {
            System.err.println("Error al listar detalles de la factura con ID " + facturaId + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al listar los detalles de la factura desde la base de datos", e);
        }
    }

    public DetalleFactura obtenerDetalleFacturaPorId(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "EXEC crudDetalleFactura @id = ?, @opcion = 5",
                    new Object[]{id},
                    detalleFacturaRowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            System.err.println("Error al obtener detalle de factura con ID " + id + ": " + e.getMessage());
            throw new DataBaseOperationException("Error al obtener el detalle de factura desde la base de datos", e);
        }
    }
    public List<DetalleFactura> listarTodosDetallesFactura() {
        try {
            return jdbcTemplate.query(
                    "EXEC crudDetalleFactura @opcion = 6",
                    detalleFacturaRowMapper
            );
        } catch (DataAccessException e) {
            System.err.println("Error al listar todos los detalles de factura: " + e.getMessage());
            throw new DataBaseOperationException("Error al listar todos los detalles de factura desde la base de datos", e);
        }
    }

}
