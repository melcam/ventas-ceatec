package com.venta.venta.repository;

import java.util.List;

import com.venta.venta.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  List<Categoria> findAllByDescripcionAndEstado(String descripcion, Boolean estado);
}