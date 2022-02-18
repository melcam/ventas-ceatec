package com.venta.venta.repository;

import java.util.List;

import com.venta.venta.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  List<Cliente> findAllByNombreAndApellidos(String nombre, String Apellidos);
}