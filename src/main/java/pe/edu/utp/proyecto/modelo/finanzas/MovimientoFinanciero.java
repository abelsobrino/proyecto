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
    private double monto;
    private LocalDate fecha;

    public boolean registrarMovimiento() {
        log.info("[Finanzas] Movimiento {} registrado. Tipo: {} | Monto: S/.{}", idMovimiento, tipo, monto);
        return true;
    }

    public boolean revertirMovimiento() {
        log.warn("[Finanzas] ALERTA: Revirtiendo movimiento {} por anulación de transacción.", idMovimiento);
        return true;
    }
}