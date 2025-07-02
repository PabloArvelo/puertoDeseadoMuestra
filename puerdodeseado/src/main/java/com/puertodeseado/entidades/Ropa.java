package com.puertodeseado.entidades;

import jakarta.persistence.*;

@Entity
public class Ropa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(unique = true)
  private String prenda;

  public Ropa() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPrenda() {
    return prenda;
  }

  public void setPrenda(String prenda) {
    this.prenda = prenda;
  }
}
