package com.venta.venta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;
import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.UnidadMedida;
import com.venta.venta.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("unidadmedidas")
@AllArgsConstructor
public class UnidadMedidaController {

  @Autowired
  private UnidadMedidaService unidadMedidaService;

  @GetMapping
  public List<UnidadMedida> listAllUnidadmedida() {
    return unidadMedidaService.listAllUnidadmedida();
  }

  @GetMapping("/{id}")
  public GeneralDTO getUnidadmedidaById(@PathVariable Long id) {
    return unidadMedidaService.getUnidadmedidaById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createUnidadmedida(@RequestBody UnidadMedida unidadMedida) {
    return ok(unidadMedidaService.createUnidadmedida(unidadMedida));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateUnidadmedida(@RequestBody UnidadMedida unidadMedida) {
    return ok(unidadMedidaService.updateUnidadmedida(unidadMedida));
  }

  @PutMapping("/inhabilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> inhabilitarUnidadmedida(@PathVariable Long id) {
    return ok(unidadMedidaService.inhabilitarUnidadmedida(id));
  }

  @PutMapping("/habilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> habilitarUnidadmedida(@PathVariable Long id) {
    return ok(unidadMedidaService.habilitarUnidadmedida(id));
  }
}