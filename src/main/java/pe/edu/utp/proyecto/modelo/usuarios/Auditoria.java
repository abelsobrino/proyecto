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
 * Representa un registro de auditoria de acciones realizadas en el sistema.
 */
@Entity
@Table(name = "auditorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private int idAuditoria;

    @Column(name = "accion", length = 100, nullable = false)
    private String accion;

    @Column(name = "fecha", length = 50, nullable = false)
    private String fecha;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}