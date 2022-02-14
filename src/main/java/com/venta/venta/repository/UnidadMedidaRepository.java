package com.venta.venta.repository;

import java.util.List;
import com.venta.venta.model.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
  List<UnidadMedida> findAllByDescripcionAndEstado(String descripcion, Boolean estado);
}