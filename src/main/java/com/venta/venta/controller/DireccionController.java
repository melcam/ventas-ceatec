package com.venta.venta.controller;

import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Direccion;
import com.venta.venta.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

@RestController
@RequestMapping("direcciones")
@AllArgsConstructor
public class DireccionController {

  @Autowired
  private DireccionService direccionService;

  @GetMapping
  public List<Direccion> listAllDireccion() {
    return direccionService.listAllDireccion();
  }

  @GetMapping("/{id}")
  public GeneralDTO getDireccionById(@PathVariable Long id) {
    return direccionService.getDireccionById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createDireccion(@RequestBody Direccion direccion) {
    return ok(direccionService.createDireccion(direccion));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateDireccion(@RequestBody Direccion direccion) {
    return ok(direccionService.updateDireccion(direccion));
  }

  @PutMapping("/inhabilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> inhabilitarDireccion(@PathVariable Long id) {
    return ok(direccionService.inhabilitarDireccion(id));
  }

  @PutMapping("/habilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> habilitarDireccion(@PathVariable Long id) {
    return ok(direccionService.habilitarDireccion(id));
  }

}