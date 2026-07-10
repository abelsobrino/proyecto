package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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