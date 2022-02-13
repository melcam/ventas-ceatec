package com.venta.venta.repository;

import java.util.List;
import com.venta.venta.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRespository extends JpaRepository<Direccion, Long> {
  List<Direccion> findAllByDescripcionAndEstado(String descripcion, Boolean estado);
}