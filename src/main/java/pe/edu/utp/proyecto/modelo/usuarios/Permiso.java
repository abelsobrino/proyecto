package pe.edu.utp.proyecto.modelo.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un permiso que puede ser asignado a un rol.
 */
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