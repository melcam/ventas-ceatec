package com.venta.venta.service;

import java.util.List;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cliente;

public interface ClienteService {
  ResponseMensaje<?> createCliente(Cliente cliente);

  List<Cliente> listAllCliente();

  Cliente getClienteById(Long id);

  ResponseMensaje<?> updateCliente(Cliente cliente);
}