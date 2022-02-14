package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.UnidadMedida;
import com.venta.venta.repository.UnidadMedidaRepository;
import com.venta.venta.service.UnidadMedidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {
  @Autowired
  private final UnidadMedidaRepository unidadMedidaRepository;

  private String validacion(UnidadMedida unidadMedida, String accion) {
    String mensaje = "";
    Integer cont = 0;
    if (accion.equals("update")) {
      cont = 1;
    }
    if (unidadMedida.getDescripcion() == null || unidadMedida.getDescripcion().isEmpty())
      return "La descripción de la Unidad de medida es requerido";

    List<UnidadMedida> list = unidadMedidaRepository.findAllByDescripcionAndEstado(unidadMedida.getDescripcion(), true);
    if (list.size() > cont) {
      return "Ya existe una unidad de medida con nombre " + unidadMedida.getDescripcion();
    }
    return mensaje;
  }

  @Override
  public ResponseMensaje<?> createUnidadmedida(UnidadMedida um) {
    String mensValidacion = this.validacion(um, "");
    if (mensValidacion.equals("")) {
      um.setEstado(true);
      unidadMedidaRepository.save(um);
      return new ResponseMensaje<>(200, "Unidad de medida " + um.getDescripcion() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public GeneralDTO getUnidadmedidaById(Long id) {
    UnidadMedida um = unidadMedidaRepository.findById(id).orElse(null);
    if (um != null) {
      GeneralDTO dto = new GeneralDTO();
      dto.setId(um.getIdunidadmedida());
      dto.setDescripcion(um.getDescripcion());
      return dto;
    } else {
      return null;
    }
  }

  @Override
  public ResponseMensaje<?> habilitarUnidadmedida(Long id) {
    if (unidadMedidaRepository.findById(id).isPresent()) {
      UnidadMedida um = unidadMedidaRepository.findById(id).orElse(null);
      String msj;
      if (um.getEstado() == false) {
        um.setEstado(true);
        unidadMedidaRepository.save(um);
        msj = "Unidad de medida " + um.getDescripcion() + " habilitado";
      } else {
        msj = "Unidad de medida " + um.getDescripcion() + " ya esta habilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Unidad de medida no encontrado");
    }
  }

  @Override
  public ResponseMensaje<?> inhabilitarUnidadmedida(Long id) {
    if (unidadMedidaRepository.findById(id).isPresent()) {
      UnidadMedida um = unidadMedidaRepository.findById(id).orElse(null);
      String msj;
      if (um.getEstado()) {
        um.setEstado(false);
        unidadMedidaRepository.save(um);
        msj = "Unidad de medida " + um.getDescripcion() + " inhabilitado";
      } else {
        msj = "Unidad de medida " + um.getDescripcion() + " ya esta inhabilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Unidad de medida no encontrado");
    }
  }

  @Override
  public List<UnidadMedida> listAllUnidadmedida() {
    return unidadMedidaRepository.findAll().stream().map(u -> {
      UnidadMedida um = new UnidadMedida();
      um.setIdunidadmedida(u.getIdunidadmedida());
      um.setDescripcion(u.getDescripcion());
      um.setEstado(u.getEstado());
      return um;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> updateUnidadmedida(UnidadMedida um) {
    String mensValidacion = this.validacion(um, "");
    if (mensValidacion.equals("")) {
      UnidadMedida umNew = unidadMedidaRepository.findById(um.getIdunidadmedida()).orElse(null);
      if (unidadMedidaRepository.findById(um.getIdunidadmedida()).isPresent()) {
        umNew.setDescripcion(um.getDescripcion());
        unidadMedidaRepository.save(umNew);
        return new ResponseMensaje<>(200, "Unidad de medida " + um.getDescripcion() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Unidad de medida no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

}