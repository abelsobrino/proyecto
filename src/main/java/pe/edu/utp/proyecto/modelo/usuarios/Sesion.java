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
 * Representa una sesion de usuario en el sistema.
 */
@Entity
@Table(name = "sesiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private int idSesion;

    @Column(name = "fecha_inicio", length = 50, nullable = false)
    private String fechaInicio;

    @Column(name = "fecha_fin", length = 50)
    private String fechaFin;

    @Column(name = "activa", nullable = false)
    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}