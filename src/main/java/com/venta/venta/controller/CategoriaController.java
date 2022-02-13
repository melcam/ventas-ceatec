package com.venta.venta.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Categoria;
import com.venta.venta.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("categorias")
@AllArgsConstructor
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public List<Categoria> listAllCategoria() {
    return categoriaService.listAllCategoria();
  }

  @GetMapping("/{id}")
  public GeneralDTO getCategoriaById(@PathVariable Long id) {
    return categoriaService.getCategoriaById(id);
  }

  @PostMapping
  public ResponseEntity<ResponseMensaje<?>> createCategoria(@RequestBody Categoria categoria) {
    return ok(categoriaService.createCategoria(categoria));
  }

  @PutMapping
  public ResponseEntity<ResponseMensaje<?>> updateCategoria(@RequestBody Categoria categoria) {
    return ok(categoriaService.updateCategoria(categoria));
  }

  @PutMapping("/inhabilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> inhabilitarCategoria(@PathVariable Long id) {
    return ok(categoriaService.inhabilitarCategoria(id));
  }

  @PutMapping("/habilitar/{id}")
  public ResponseEntity<ResponseMensaje<?>> ihabilitarCategorian(@PathVariable Long id) {
    return ok(categoriaService.habilitarCategoria(id));
  }
}