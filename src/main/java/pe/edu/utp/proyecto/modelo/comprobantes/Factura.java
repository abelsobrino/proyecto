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

@Setter
@Getter
@Entity
@Table(name = "factura")
@Slf4j
public class Factura extends ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    private String rucCliente;

    public Factura() {
        super();
    }

    public Factura(String serie, String numero, Date fechaEmision, double total, String rucCliente) {
        super(serie, numero, fechaEmision, total);
        this.rucCliente = rucCliente;
    }

    @Override
    public void emitir() {
        log.info("[Factura] Emitiendo factura legal XML/PDF para el RUC: {}", rucCliente);
    }

    @Override
    public double calcularTotal() {
        double subtotal = total;
        double igv = subtotal * 0.18;
        double totalConImpuesto = subtotal + igv;
        log.info("[Factura] Calculando total (Subtotal: {} + IGV 18%: {}) = {}", subtotal, igv, totalConImpuesto);
        return totalConImpuesto;
    }

    public boolean validarRUC() {
        boolean esValido = rucCliente != null && rucCliente.length() == 11;
        log.info("[Factura] Validando formato de RUC ({}): {}", rucCliente, esValido ? "VÁLIDO" : "INVÁLIDO");
        return esValido;
    }
}