package com.biblioteca.backend.service;

import com.biblioteca.backend.exception.ResourceNotFoundException;
import com.biblioteca.backend.model.DetalleFactura;
import com.biblioteca.backend.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaService {
    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public int crearDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.crearDetalleFactura(detalleFactura);
    }
    public int actualizarDetalleFactura(int id, DetalleFactura detalleFactura) {
        DetalleFactura detalleExistente = detalleFacturaRepository.obtenerDetalleFacturaPorId(id);
        if (detalleExistente == null) {
            throw new ResourceNotFoundException("No se encontró el detalle de la factura con el Id numero " + id);
        }
        detalleFactura.setId(id);
        return detalleFacturaRepository.actualizarDetalleFactura(id, detalleFactura);
    }

    public int eliminarDetalleFactura(int id) {
        DetalleFactura detalleExistente = detalleFacturaRepository.obtenerDetalleFacturaPorId(id);
        if (detalleExistente == null) {
            throw new ResourceNotFoundException("No se encontró el detalle de la factura con el Id numero " + id);
        }
        return detalleFacturaRepository.eliminarDetalleFactura(id);
    }

    public List<DetalleFactura> listarDetallesPorFactura(int facturaId) {
        return detalleFacturaRepository.listarDetallesPorFactura(facturaId);
    }

    public DetalleFactura obtenerDetalleFacturaPorId(int id) {
        DetalleFactura detalle = detalleFacturaRepository.obtenerDetalleFacturaPorId(id);
        if (detalle == null) {
            throw new ResourceNotFoundException("No se encontró el detalle de la factura con el Id numero " + id);
        }
        return detalle;
    }
    public List<DetalleFactura> listarTodosDetallesFactura() {
        return detalleFacturaRepository.listarTodosDetallesFactura();
    }

}