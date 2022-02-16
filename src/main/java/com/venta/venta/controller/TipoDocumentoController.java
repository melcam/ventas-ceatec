package com.venta.venta.controller;

import java.util.List;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.TipoDocumento;
import com.venta.venta.service.TipoDocumentoService;
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
@RequestMapping("tipodocumentos")
@AllArgsConstructor
public class TipoDocumentoController {

  @Autowired
  private TipoDocumentoService tipoDocumentoService;

  @GetMapping
  public List<TipoDocumento> listAllTipodocumento() {
    return tipoDocumentoService.listAllTipodocumento();
  }

  @GetMapping("/{id}")
  public TipoDocumento getTipodocumentoById(@PathVariable Long id) {
    return tipoDocumentoService.getTipodocumentoById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createTipodocumento(@RequestBody TipoDocumento tipoDocumento) {
    return ok(tipoDocumentoService.createTipodocumento(tipoDocumento));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateTipodocumento(@RequestBody TipoDocumento tipoDocumento) {
    return ok(tipoDocumentoService.updateTipodocumento(tipoDocumento));
  }

}
