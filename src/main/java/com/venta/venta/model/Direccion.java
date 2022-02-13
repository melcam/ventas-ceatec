package com.venta.venta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Getter
@Setter
public class Direccion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iddireccion;

  @Column(nullable = false)
  @NotEmpty(message = "Ingrese una descripcion para la direccion")
  private String descripcion;
  private Boolean estado;
}