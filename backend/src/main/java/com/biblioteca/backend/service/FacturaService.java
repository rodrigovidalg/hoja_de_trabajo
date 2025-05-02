package com.biblioteca.backend.service;

import com.biblioteca.backend.exception.ResourceNotFoundException;
import com.biblioteca.backend.model.Factura;
import com.biblioteca.backend.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public int crearFactura(Factura factura) {
        return facturaRepository.crearFactura(factura);
    }

    public int actualizarFactura(int id, Factura factura) {
        Factura facturaExistente = facturaRepository.obtenerFacturaPorId(id);
        if (facturaExistente == null) {
            throw new ResourceNotFoundException("No se encontró la factura con ID: " + id);
        }
        factura.setId(id);
        return facturaRepository.actualizarFactura(factura);
    }

    public int eliminarFactura(int id) {
        Factura facturaExistente = facturaRepository.obtenerFacturaPorId(id);
        if (facturaExistente == null) {
            throw new ResourceNotFoundException("No se encontró la factura con ID: " + id);
        }
        return facturaRepository.eliminarFactura(id);
    }

    public List<Factura> listarFacturas() {
        return facturaRepository.listarFacturas();
    }

    public Factura obtenerFacturaPorId(int id) {
        Factura factura = facturaRepository.obtenerFacturaPorId(id);
        if (factura == null) {
            throw new ResourceNotFoundException("No se encontró la factura con ID: " + id);
        }
        return factura;
    }
}
