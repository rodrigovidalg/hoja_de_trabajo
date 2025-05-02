package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.DetalleFactura;
import jakarta.validation.Valid;
import com.biblioteca.backend.model.Factura;
import com.biblioteca.backend.service.DetalleFacturaService;
import com.biblioteca.backend.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas() {
        return new ResponseEntity<>(facturaService.listarFacturas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable int id) {
        Factura factura = facturaService.obtenerFacturaPorId(id);
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Integer> crearFactura(@Valid @RequestBody Factura factura) {
        int idNuevaFactura = facturaService.crearFactura(factura);
        return new ResponseEntity<>(idNuevaFactura, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Integer> actualizarFactura(@PathVariable int id, @Valid @RequestBody Factura factura) {
        int resultado = facturaService.actualizarFactura(id, factura);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Integer> eliminarFactura(@PathVariable int id) {
        int resultado = facturaService.eliminarFactura(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleFactura>> obtenerDetallesPorFactura(@PathVariable int id) {
        List<DetalleFactura> detalles = detalleFacturaService.listarDetallesPorFactura(id);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
}
