package pe.edu.utp.proyecto.modelo.finanzas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa un movimiento financiero (ingreso o egreso).
 */
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

    /**
     * Registra el movimiento financiero.
     * @return true si el registro fue exitoso.
     */
    public boolean registrarMovimiento() {
        log.info("Movimiento {} registrado. Tipo: {} | Monto: S/.{}", idMovimiento, tipo, monto);
        return true;
    }

    /**
     * Revierte un movimiento financiero.
     * @return true si la reversal fue exitosa.
     */
    public boolean revertirMovimiento() {
        log.warn("ALERTA: Revirtiendo movimiento {} por anulacion de transaccion.", idMovimiento);
        return true;
    }
}