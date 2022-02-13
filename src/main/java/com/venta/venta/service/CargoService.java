package com.venta.venta.service;

import java.util.List;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cargo;

public interface CargoService {
  ResponseMensaje<?> createCargo(Cargo cargo);

  List<Cargo> listAllCargo();

  GeneralDTO getCargoById(Long id);

  ResponseMensaje<?> updateCargo(Cargo cargo);

  ResponseMensaje<?> inhabilitarCargo(Long id);

  ResponseMensaje<?> habilitarCargo(Long id);
}