package pe.edu.utp.proyecto.modelo.comprobantes;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_credito")
public class NotaCredito extends ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motivo;

    // Constructor vacío
    public NotaCredito() {
        super();
    }

    // Constructor con parámetros (llama a super)
    public NotaCredito(String serie, String numero, Date fechaEmision, double total, String motivo) {
        super(serie, numero, fechaEmision, total);
        this.motivo = motivo;
    }

    @Override
    public void emitir() {
        System.out.println("[Nota Crédito] Emitiendo nota de ajuste por motivo: " + motivo);
    }

    @Override
    public double calcularTotal() {
        System.out.println("[Nota Crédito] Procesando saldo a favor / devolución de: " + this.total);
        return this.total;
    }

    public void aplicarDescuento() {
        System.out.println("[Nota Crédito] Descuento financiero aplicado correctamente al saldo original.");
    }
}
