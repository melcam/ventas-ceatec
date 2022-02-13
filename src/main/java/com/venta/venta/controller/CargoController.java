package com.venta.venta.controller;

import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cargo;
import com.venta.venta.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("cargos")
public class CargoController {

  @Autowired
  private final CargoService cargoService;

  @GetMapping
  public List<Cargo> listAllCargo() {
    return cargoService.listAllCargo();
  }

  @GetMapping("/{id}")
  public GeneralDTO getCargoById(@PathVariable Long id) {
    return cargoService.getCargoById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createCargo(@RequestBody Cargo cargo) {
    return ok(cargoService.createCargo(cargo));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateCargo(@RequestBody Cargo cargo) {
    return ok(cargoService.updateCargo(cargo));
  }

  @PutMapping("/inhabilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> inhabilitarCargo(@PathVariable Long id) {
    return ok(cargoService.inhabilitarCargo(id));
  }

  @PutMapping("/habilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> habilitarCargo(@PathVariable Long id) {
    return ok(cargoService.habilitarCargo(id));
  }

}