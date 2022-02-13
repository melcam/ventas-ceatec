package com.venta.venta.dto;

import org.springframework.lang.Nullable;

public class ResponseMensaje<T> {

  private Integer codigo;
  private String mensaje;
  private T objeto;

  public ResponseMensaje(Integer codigo, String mensaje, @Nullable T objeto) {
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.objeto = objeto;
  }

  public ResponseMensaje(Integer codigo, String mensaje) {
    this.codigo = codigo;
    this.mensaje = mensaje;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public T getObjeto() {
    return objeto;
  }

  public void setObjeto(T objeto) {
    this.objeto = objeto;
  }
}