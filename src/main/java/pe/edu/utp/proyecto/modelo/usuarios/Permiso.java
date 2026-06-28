package pe.edu.utp.proyecto.modelo.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private int idPermiso;

    @Column(name = "nombre_permiso", length = 100, nullable = false, unique = true)
    private String nombrePermiso;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}