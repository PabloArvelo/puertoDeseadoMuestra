package com.puertodeseado.entidades.seguridad;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rol;


    @OneToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY, mappedBy = "rol")
    private List<Usuario> usuarios;


    // lo hacemos many to many por que un rol puede tener muchos permisos
    // y un permiso puede pertenecer a muchos roles
    @ManyToMany(targetEntity = Permiso.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rol_permiso", // definimos nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "rol_id"), // definimos nombre de columna que referencia a "rol"
            inverseJoinColumns = @JoinColumn(name = "permiso_id") // definimos  nombre de columa que referencia a "permiso"
    )
    private Set<Permiso> listaPermisos = new HashSet<>();
}


