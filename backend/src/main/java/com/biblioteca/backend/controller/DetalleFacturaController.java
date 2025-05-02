package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.DetalleFactura;
import com.biblioteca.backend.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-factura")
public class DetalleFacturaController {
    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> listarDetallesFactura() {
        List<DetalleFactura> detalles = detalleFacturaService.listarTodosDetallesFactura();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerDetalleFacturaPorId(@PathVariable int id) {
        DetalleFactura detalle = detalleFacturaService.obtenerDetalleFacturaPorId(id);
        return new ResponseEntity<>(detalle, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Integer> crearDetalleFactura(@Valid @RequestBody DetalleFactura detalleFactura) {
        int idNuevoDetalle = detalleFacturaService.crearDetalleFactura(detalleFactura);
        return new ResponseEntity<>(idNuevoDetalle, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Integer> actualizarDetalleFactura(@PathVariable int id, @Valid @RequestBody DetalleFactura detalleFactura) {
        int resultado = detalleFacturaService.actualizarDetalleFactura(id, detalleFactura);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Integer> eliminarDetalleFactura(@PathVariable int id) {
        int resultado = detalleFacturaService.eliminarDetalleFactura(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
