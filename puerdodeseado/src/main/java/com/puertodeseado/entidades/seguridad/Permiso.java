package com.puertodeseado.entidades.seguridad;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String permiso;

//    comenté lo de abajo porque si no se crean dos tablas identicas
//    entonces ponemos el manytomany de un solo lado (en una sola entidad)




    // En esta clase Permiso, no usamos @JoinTable en el atributo roles
    // solo ponemos mappedBy = "permisos", porque la tabla intermedia ya está definida en Rol.
    // No es obligatorio, pero es recomendable para que la relación sea bidireccional.
//    @ManyToMany(targetEntity = Rol.class,fetch = FetchType.LAZY, mappedBy = "listaPermisos")
//    private Set<Rol> listaRoles = new HashSet<>();
}
