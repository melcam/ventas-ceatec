package com.venta.venta.repository;

import java.util.List;
import com.venta.venta.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
  List<Cargo> findAllByDescripcionAndEstado(String descripcion,Boolean estado);

}