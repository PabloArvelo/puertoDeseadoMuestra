package com.puertodeseado.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RopaTalles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String talle;

  public RopaTalles() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTalle() {
    return talle;
  }

  public void setTalle(String talle) {
    this.talle = talle;
  }
}
