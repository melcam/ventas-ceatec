package com.venta.venta.service;

import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.UnidadMedida;

public interface UnidadMedidaService {
  ResponseMensaje<?> createUnidadmedida(UnidadMedida um);

  List<UnidadMedida> listAllUnidadmedida();

  GeneralDTO getUnidadmedidaById(Long id);

  ResponseMensaje<?> updateUnidadmedida(UnidadMedida um);

  ResponseMensaje<?> inhabilitarUnidadmedida(Long id);

  ResponseMensaje<?> habilitarUnidadmedida(Long id);
}