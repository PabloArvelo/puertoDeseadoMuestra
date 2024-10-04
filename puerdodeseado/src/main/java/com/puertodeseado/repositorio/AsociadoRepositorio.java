package com.puertodeseado.repositorio;


import com.puertodeseado.entidades.AsociadosMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociadoRepositorio extends JpaRepository<AsociadosMain, String> {


}
