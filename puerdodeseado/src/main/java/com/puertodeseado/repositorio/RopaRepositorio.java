package com.puertodeseado.repositorio;

import com.puertodeseado.entidades.Ropa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RopaRepositorio extends JpaRepository<Ropa, Integer> {


}
