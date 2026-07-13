package pe.edu.utp.proyecto.modelo.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un usuario del sistema.
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "correo", length = 150, nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}