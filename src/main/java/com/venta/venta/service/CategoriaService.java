package com.venta.venta.service;

import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Categoria;

public interface CategoriaService {
  ResponseMensaje<?> createCategoria(Categoria categoria);

  List<Categoria> listAllCategoria();

  GeneralDTO getCategoriaById(Long id);

  ResponseMensaje<?> updateCategoria(Categoria categoria);

  ResponseMensaje<?> inhabilitarCategoria(Long id);

  ResponseMensaje<?> habilitarCategoria(Long id);
}