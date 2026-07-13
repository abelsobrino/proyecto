package pe.edu.utp.proyecto.modelo.ventas;

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
 * Representa un metodo de pago (efectivo, tarjeta, etc.).
 */
@Entity
@Table(name = "metodos_pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo")
    private int idMetodo;

    @Column(name = "nombre_metodo", length = 50, nullable = false, unique = true)
    private String nombreMetodo;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}