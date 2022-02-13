package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cargo;
import com.venta.venta.repository.CargoRepository;
import com.venta.venta.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CargoServiceImpl implements CargoService {
  @Autowired
  private final CargoRepository cargoRepository;

  private String validacion(Cargo cargo, String accion) {
    String mensaje = "";
    Integer cont = 0;
    if (accion.equals("update"))
      cont = 1;

    if (cargo.getDescripcion() == null || cargo.getDescripcion().isEmpty())
      return "La descripción del cargo es requerido";
    // Controlar que no haya registro duplicado
    List<Cargo> lista = cargoRepository.findAllByDescripcionAndEstado(cargo.getDescripcion(), true);
    if (lista.size() > cont)
      return "Ya existe un cargo con nombre " + cargo.getDescripcion();

    return mensaje;
  }

  @Override
  public List<Cargo> listAllCargo() {
    return cargoRepository.findAll().stream().map(c -> {
      Cargo cargo = new Cargo();
      cargo.setIdcargo(c.getIdcargo());
      cargo.setDescripcion(c.getDescripcion());
      cargo.setEstado(c.getEstado());
      return cargo;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> createCargo(Cargo cargo) {
    String mensValidacion = this.validacion(cargo, "");
    if (mensValidacion.equals("")) {
      cargo.setEstado(true);
      cargoRepository.save(cargo);
      return new ResponseMensaje<>(200, "Cargo " + cargo.getDescripcion() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public GeneralDTO getCargoById(Long id) {
    Cargo cargo = cargoRepository.findById(id).orElse(null);
    if (cargo != null) {
      GeneralDTO dto = new GeneralDTO();
      dto.setId(cargo.getIdcargo());
      dto.setDescripcion(cargo.getDescripcion());
      return dto;
    } else {
      return null;
    }
  }

  @Override
  public ResponseMensaje<?> updateCargo(Cargo cargo) {
    String mensValidacion = this.validacion(cargo, "update");
    if (mensValidacion.equals("")) {
      Cargo cargoNew = cargoRepository.findById(cargo.getIdcargo()).orElse(null);
      if (cargoRepository.findById(cargo.getIdcargo()).isPresent()) {
        cargoNew.setDescripcion(cargo.getDescripcion());
        cargoRepository.save(cargoNew);
        return new ResponseMensaje<>(200, "Cargo " + cargo.getDescripcion() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Cargo no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public ResponseMensaje<?> inhabilitarCargo(Long id) {
    if (cargoRepository.findById(id).isPresent()) {
      Cargo cargo = cargoRepository.findById(id).orElse(null);
      String msj;
      if (cargo.getEstado()) {
        cargo.setEstado(false);
        cargoRepository.save(cargo);
        msj = "Cargo " + cargo.getDescripcion() + " inhabilitado";
      } else {
        msj = "Cargo " + cargo.getDescripcion() + " ya esta inhabilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Cargo no encontrado");
    }
  }

  @Override
  public ResponseMensaje<?> habilitarCargo(Long id) {
    if (cargoRepository.findById(id).isPresent()) {
      Cargo cargo = cargoRepository.findById(id).orElse(null);
      String msj;
      if (cargo.getEstado() == false) {
        cargo.setEstado(true);
        cargoRepository.save(cargo);
        msj = "Cargo " + cargo.getDescripcion() + " habilitado";
      } else {
        msj = "Cargo " + cargo.getDescripcion() + " ya esta habilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Cargo no encontrado");
    }
  }

}