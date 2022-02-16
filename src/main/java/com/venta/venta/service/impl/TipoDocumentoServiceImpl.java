package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.TipoDocumento;
import com.venta.venta.repository.TipoDocumentoRepository;
import com.venta.venta.service.TipoDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

  @Autowired
  private final TipoDocumentoRepository tipoDocumentoRepository;

  private String validacion(TipoDocumento tipoDocumento, String accion) {
    String mensaje = "";
    int cont = 0;
    if (accion.equals("update"))
      cont = 1;
    if (tipoDocumento.getDescripcion() == null || tipoDocumento.getDescripcion().isBlank()) {
      return "La descripción del Tipo de Documento es requerido";
    }
    List<TipoDocumento> list = tipoDocumentoRepository.findAllByDescripcion(tipoDocumento.getDescripcion());
    if (list.size() > cont) {
      return "Ya existe un tipo de documento con nombre " + tipoDocumento.getDescripcion();
    }
    return mensaje;
  }

  @Override
  public ResponseMensaje<?> createTipodocumento(TipoDocumento td) {
    String mensValidacion = this.validacion(td, "");
    if (mensValidacion.equals("")) {
      tipoDocumentoRepository.save(td);
      return new ResponseMensaje<>(200, "Tipo de documento " + td.getDescripcion() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public TipoDocumento getTipodocumentoById(Long id) {
    TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id).orElse(null);
    if (tipoDocumento != null) {
      TipoDocumento tp = new TipoDocumento();
      tp.setIdtipodocumento(tipoDocumento.getIdtipodocumento());
      tp.setDescripcion(tipoDocumento.getDescripcion());
      tp.setCode_sunat(tipoDocumento.getCode_sunat());
      return tp;
    } else {
      return null;
    }
  }

  @Override
  public List<TipoDocumento> listAllTipodocumento() {
    return tipoDocumentoRepository.findAll().stream().map(td -> {
      TipoDocumento tipoDocumento = new TipoDocumento();
      tipoDocumento.setIdtipodocumento(td.getIdtipodocumento());
      tipoDocumento.setDescripcion(td.getDescripcion());
      tipoDocumento.setCode_sunat(td.getCode_sunat());
      return tipoDocumento;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> updateTipodocumento(TipoDocumento td) {
    String mensValidacion = this.validacion(td, "update");
    if (mensValidacion.equals("")) {
      TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(td.getIdtipodocumento()).orElse(null);
      if (tipoDocumentoRepository.findById(td.getIdtipodocumento()).isPresent()) {
        tipoDocumento.setDescripcion(td.getDescripcion());
        tipoDocumento.setCode_sunat(td.getCode_sunat());
        tipoDocumentoRepository.save(td);
        return new ResponseMensaje<>(200, "Tipo de documento " + td.getDescripcion() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Tipo de documento no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

}
