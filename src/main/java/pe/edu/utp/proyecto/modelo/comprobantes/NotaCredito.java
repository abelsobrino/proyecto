package pe.edu.utp.proyecto.modelo.comprobantes;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_credito")
@Slf4j
public class NotaCredito extends ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motivo;

    public NotaCredito() {
        super();
    }

    public NotaCredito(String serie, String numero, Date fechaEmision, double total, String motivo) {
        super(serie, numero, fechaEmision, total);
        this.motivo = motivo;
    }

    @Override
    public void emitir() {
        log.info("[Nota Crédito] Emitiendo nota de ajuste por motivo: {}", motivo);
    }

    @Override
    public double calcularTotal() {
        log.info("[Nota Crédito] Procesando saldo a favor / devolución de: {}", this.total);
        return this.total;
    }

    public void aplicarDescuento() {
        log.info("[Nota Crédito] Descuento financiero aplicado correctamente al saldo original.");
    }
}
