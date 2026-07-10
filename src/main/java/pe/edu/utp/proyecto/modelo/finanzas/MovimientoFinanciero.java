package pe.edu.utp.proyecto.modelo.finanzas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimiento_financiero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MovimientoFinanciero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimiento;

    private String tipo;

    private BigDecimal monto;

    private LocalDate fecha;

    public boolean registrarMovimiento() {
        log.info("Movimiento {} registrado. Tipo: {} | Monto: S/.{}", idMovimiento, tipo, monto);
        return true;
    }

    public boolean revertirMovimiento() {
        log.warn("ALERTA: Revirtiendo movimiento {} por anulacion de transaccion.", idMovimiento);
        return true;
    }
}