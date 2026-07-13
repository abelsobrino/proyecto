package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Representa un movimiento de inventario (entrada o salida de productos).
 */
@Entity
@Table(name = "movimientos_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int idMovimiento;

    @Column(name = "tipo_movimiento", length = 10, nullable = false)
    private String tipoMovimiento;

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

    /**
     * Registra el movimiento de inventario en los logs.
     */
    public void registrarMovimiento() {
        log.info("{} de {} unidades - Producto: {}", tipoMovimiento, cantidad, producto.getNombre());
    }
}