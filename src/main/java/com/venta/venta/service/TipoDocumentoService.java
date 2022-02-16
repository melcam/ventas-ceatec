package com.venta.venta.service;

import java.util.List;

import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.TipoDocumento;

public interface TipoDocumentoService {
  ResponseMensaje<?> createTipodocumento(TipoDocumento td);

  List<TipoDocumento> listAllTipodocumento();

  TipoDocumento getTipodocumentoById(Long id);

  ResponseMensaje<?> updateTipodocumento(TipoDocumento td);
}
