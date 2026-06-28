package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "movimientos_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int idMovimiento;

    @Column(name = "tipo_movimiento", length = 10, nullable = false)
    private String tipoMovimiento; // "ENTRADA" o "SALIDA"

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "fecha_movimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public void registrarMovimiento() {
        System.out.println("[MovimientoInventario] " + tipoMovimiento
                + " de " + cantidad + " unidades - ProductoService: " + producto.getNombre());
    }
}