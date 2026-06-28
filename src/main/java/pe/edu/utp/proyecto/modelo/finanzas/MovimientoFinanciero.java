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

import java.time.LocalDate;

@Entity
@Table(name = "movimiento_financiero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoFinanciero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimiento;

    private String tipo;
    private double monto;
    private LocalDate fecha;

    public boolean registrarMovimiento() {
        System.out.println("[Finanzas] Movimiento " + idMovimiento +
                " registrado. Tipo: " + tipo + " | Monto: S/." + monto);
        return true;
    }

    public boolean revertirMovimiento() {
        System.out.println("[Finanzas] ALERTA: Revirtiendo movimiento " +
                idMovimiento + " por anulación de transacción.");
        return true;
    }
}
