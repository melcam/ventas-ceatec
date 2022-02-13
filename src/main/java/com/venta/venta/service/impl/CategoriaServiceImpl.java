package com.venta.venta.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.venta.venta.dto.GeneralDTO;
import com.venta.venta.dto.ResponseMensaje;
import com.venta.venta.model.Categoria;
import com.venta.venta.repository.CategoriaRepository;
import com.venta.venta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

  @Autowired
  private final CategoriaRepository categoriaRepository;

  private String validacion(Categoria categoria, String accion) {
    String mensaje = "";
    Integer cont = 0;
    if (accion.equals("update"))
      cont = 1;

    if (categoria.getDescripcion() == null || categoria.getDescripcion().isEmpty())
      return "La descripción de la Categoría es requerido";
    // Controlar que no haya registro duplicado
    List<Categoria> lista = categoriaRepository.findAllByDescripcionAndEstado(categoria.getDescripcion(), true);
    if (lista.size() > cont)
      return "Ya existe una categoría con nombre " + categoria.getDescripcion();

    return mensaje;
  }

  @Override
  public ResponseMensaje<?> createCategoria(Categoria categoria) {
    String mensValidacion = this.validacion(categoria, "");
    if (mensValidacion.equals("")) {
      categoria.setEstado(true);
      categoriaRepository.save(categoria);
      return new ResponseMensaje<>(200, "Categoría " + categoria.getDescripcion() + " creado");
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

  @Override
  public GeneralDTO getCategoriaById(Long id) {
    Categoria categoria = categoriaRepository.findById(id).orElse(null);
    if (categoria != null) {
      GeneralDTO dto = new GeneralDTO();
      dto.setId(categoria.getIdcategoria());
      dto.setDescripcion(categoria.getDescripcion());
      return dto;
    } else {
      return null;
    }
  }

  @Override
  public ResponseMensaje<?> habilitarCategoria(Long id) {
    if (categoriaRepository.findById(id).isPresent()) {
      Categoria categoria = categoriaRepository.findById(id).orElse(null);
      String msj;
      if (categoria.getEstado() == false) {
        categoria.setEstado(true);
        categoriaRepository.save(categoria);
        msj = "Categoría " + categoria.getDescripcion() + " esta habilitado";
      } else {
        msj = "Categoría " + categoria.getDescripcion() + " ya esta habilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Categoría no encontrado");
    }
  }

  @Override
  public ResponseMensaje<?> inhabilitarCategoria(Long id) {
    if (categoriaRepository.findById(id).isPresent()) {
      Categoria categoria = categoriaRepository.findById(id).orElse(null);
      String msj;
      if (categoria.getEstado()) {
        categoria.setEstado(false);
        categoriaRepository.save(categoria);
        msj = "Categoría " + categoria.getDescripcion() + " esta inhabilitado";
      } else {
        msj = "Categoría " + categoria.getDescripcion() + " ya esta inhabilitado";
      }
      return new ResponseMensaje<>(200, msj);
    } else {
      return new ResponseMensaje<>(409, "Categoría no encontrado");
    }
  }

  @Override
  public List<Categoria> listAllCategoria() {
    return categoriaRepository.findAll().stream().map(c -> {
      Categoria categoria = new Categoria();
      categoria.setIdcategoria(c.getIdcategoria());
      categoria.setDescripcion(c.getDescripcion());
      categoria.setEstado(c.getEstado());
      return categoria;
    }).collect(Collectors.toList());
  }

  @Override
  public ResponseMensaje<?> updateCategoria(Categoria categoria) {
    String mensValidacion = this.validacion(categoria, "update");
    if (mensValidacion.equals("")) {
      Categoria categoriaNew = categoriaRepository.findById(categoria.getIdcategoria()).orElse(null);
      if (categoriaRepository.findById(categoria.getIdcategoria()).isPresent()) {
        categoriaNew.setDescripcion(categoria.getDescripcion());
        categoriaRepository.save(categoriaNew);
        return new ResponseMensaje<>(200, "Categoría " + categoria.getDescripcion() + " actualizado");
      } else {
        return new ResponseMensaje<>(409, "Categoría no encontrado");
      }
    } else {
      return new ResponseMensaje<>(404, mensValidacion);
    }
  }

}