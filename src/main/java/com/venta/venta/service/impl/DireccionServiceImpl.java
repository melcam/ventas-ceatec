package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Direccion;
import com.venta.venta.repository.DireccionRespository;
import com.venta.venta.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DireccionServiceImpl implements DireccionService {

  @Autowired
  private final DireccionRespository direccionRespository;

  private String validacion(Direccion direccion, String accion) {
    String mensaje = "";
    Integer cont = 0;
    if (accion.equals("update"))
      cont = 1;

    if (direccion.getDescripcion() == null || direccion.getDescripcion().isEmpty())
      return "La descripción de la Direccion es requerido";
    // Controlar que no haya registro duplicado
    List<Direccion> lista = direccionRespository.findAllByDescripcionAndEstado(direccion.getDescripcion(), true);
    if (lista.size() > cont)
      return "Ya existe una direccion con nombre " + direccion.getDescripcion();

    return mensaje;
  }

  @Override
  public ResponseMensaje<?> createDireccion(Direccion direccion) {
    String mensValidacion = this.validacion(direccion, "");
    if (mensValidacion.equals("")) {
      direccion.setEstado(true);
      direccionRespository.save(direccion);
      return new ResponseMensaje<>(200, "Dirección " + direccion.getDescripcion() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public GeneralDTO getDireccionById(Long id) {
    Direccion direccion = direccionRespository.findById(id).orElse(null);
    if (direccion != null) {
      GeneralDTO dto = new GeneralDTO();
      dto.setId(direccion.getIddireccion());
      dto.setDescripcion(direccion.getDescripcion());
      return dto;
    } else {
      return null;
    }
  }

  @Override
  public ResponseMensaje<?> habilitarDireccion(Long id) {
    if (direccionRespository.findById(id).isPresent()) {
      Direccion direccion = direccionRespository.findById(id).orElse(null);
      String msj;
      if (direccion.getEstado() == false) {
        direccion.setEstado(true);
        direccionRespository.save(direccion);
        msj = "Dirección " + direccion.getDescripcion() + " habilitado";
      } else {
        msj = "Dirección " + direccion.getDescripcion() + " ya esta habilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Dirección no encontrado");
    }
  }

  @Override
  public ResponseMensaje<?> inhabilitarDireccion(Long id) {
    if (direccionRespository.findById(id).isPresent()) {
      Direccion direccion = direccionRespository.findById(id).orElse(null);
      String msj;
      if (direccion.getEstado()) {
        direccion.setEstado(false);
        direccionRespository.save(direccion);
        msj = "Dirección " + direccion.getDescripcion() + " inhabilitado";
      } else {
        msj = "Dirección " + direccion.getDescripcion() + " ya esta inhabilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Dirección no encontrado");
    }
  }

  @Override
  public List<Direccion> listAllDireccion() {
    return direccionRespository.findAll().stream().map(c -> {
      Direccion direccion = new Direccion();
      direccion.setIddireccion(c.getIddireccion());
      direccion.setDescripcion(c.getDescripcion());
      direccion.setEstado(c.getEstado());
      return direccion;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> updateDireccion(Direccion direccion) {
    String mensValidacion = this.validacion(direccion, "update");
    if (mensValidacion.equals("")) {
      Direccion direccionNew = direccionRespository.findById(direccion.getIddireccion()).orElse(null);
      if (direccionRespository.findById(direccion.getIddireccion()).isPresent()) {
        direccionNew.setDescripcion(direccion.getDescripcion());
        direccionRespository.save(direccionNew);
        return new ResponseMensaje<>(200, "Dirección " + direccion.getDescripcion() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Dirección no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

}