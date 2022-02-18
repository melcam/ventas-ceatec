package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cliente;
import com.venta.venta.repository.ClienteRepository;
import com.venta.venta.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private final ClienteRepository clienteRepository;

  private String validacion(Cliente cliente, String accion) {
    String mensaje = "";
    Integer cont = 0;
    if (accion.equals("update")) {
      cont = 1;
    }
    if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
      return "El nombre del cliente es requerido";
    }
    if (cliente.getApellidos() == null || cliente.getApellidos().isEmpty()) {
      return "Los apelllidos del cliente es requerido";
    }
    List<Cliente> list = clienteRepository.findAllByNombreAndApellidos(cliente.getNombre(), cliente.getApellidos());
    if (list.size() > cont) {
      return "Ya existe un cliente con nombre: " + cliente.getNombre() + " y apellidos: " + cliente.getApellidos();
    }
    return mensaje;
  }

  @Override
  public ResponseMensaje<?> createCliente(Cliente cliente) {
    String mensValidacion = this.validacion(cliente, "");
    if (mensValidacion.equals("")) {
      clienteRepository.save(cliente);
      return new ResponseMensaje<>(200, "Cliente " + cliente.getNombre() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public Cliente getClienteById(Long id) {
    Cliente cliente = clienteRepository.findById(id).orElse(null);
    if (cliente != null) {
      Cliente setCliente = new Cliente();
      setCliente.setIdcliente(cliente.getIdcliente());
      setCliente.setNombre(cliente.getNombre());
      setCliente.setApellidos(cliente.getApellidos());
      setCliente.setEdad(cliente.getEdad() != null ? cliente.getEdad() : null);
      setCliente.setGenero(cliente.getGenero() != null ? cliente.getGenero() : null);
      return setCliente;
    } else {
      return null;
    }
  }

  @Override
  public List<Cliente> listAllCliente() {
    return clienteRepository.findAll().stream().map(c -> {
      Cliente cliente = new Cliente();
      cliente.setIdcliente(c.getIdcliente());
      cliente.setNombre(c.getNombre());
      cliente.setApellidos(c.getApellidos());
      cliente.setEdad(c.getEdad() != null ? c.getEdad() : null);
      cliente.setGenero(c.getGenero() != null ? c.getGenero() : null);
      return cliente;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> updateCliente(Cliente cliente) {
    String mensValidacion = this.validacion(cliente, "update");
    if (mensValidacion.equals("")) {
      Cliente clienteNew = clienteRepository.findById(cliente.getIdcliente()).orElse(null);
      if (clienteNew != null) {
        clienteNew.setNombre(cliente.getNombre());
        clienteNew.setApellidos(cliente.getApellidos());
        clienteNew.setEdad(cliente.getEdad());
        clienteNew.setGenero(cliente.getGenero());
        clienteRepository.save(clienteNew);
        return new ResponseMensaje<>(200, "Cliente " + cliente.getNombre() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Cliente no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

}