package com.venta.venta.repository;

import java.util.List;
import com.venta.venta.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
  List<TipoDocumento> findAllByDescripcion(String descripcion);
}
