package com.venta.venta.service;

import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Direccion;

public interface DireccionService {
  ResponseMensaje<?> createDireccion(Direccion direccion);

  List<Direccion> listAllDireccion();

  GeneralDTO getDireccionById(Long id);

  ResponseMensaje<?> updateDireccion(Direccion direccion);

  ResponseMensaje<?> inhabilitarDireccion(Long id);

  ResponseMensaje<?> habilitarDireccion(Long id);
}