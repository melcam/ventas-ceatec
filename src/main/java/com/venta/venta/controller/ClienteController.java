package com.venta.venta.controller;

import java.util.List;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Cliente;
import com.venta.venta.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("clientes")
@AllArgsConstructor
public class ClienteController {
  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public List<Cliente> listAllCliente() {
    return clienteService.listAllCliente();
  }

  @GetMapping("/{id}")
  public Cliente getClienteById(@PathVariable Long id) {
    return clienteService.getClienteById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createCliente(@RequestBody Cliente cliente) {
    return ok(clienteService.createCliente(cliente));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateCliente(@RequestBody Cliente cliente) {
    return ok(clienteService.updateCliente(cliente));
  }
}