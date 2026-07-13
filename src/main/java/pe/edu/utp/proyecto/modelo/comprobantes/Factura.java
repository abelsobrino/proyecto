package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Representa una factura legal.
 * Extiende de ComprobanteElectronico y agrega el RUC del cliente.
 */
@Setter
@Getter
@Entity
@Table(name = "factura")
@Slf4j
public class Factura extends ComprobanteElectronico {

    private static final double IGV = 0.18;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    /** RUC del cliente que realiza la compra */
    private String rucCliente;

    public Factura() {
        super();
    }

    public Factura(String serie, String numero, LocalDate fechaEmision, double total, String rucCliente) {
        super(serie, numero, fechaEmision, total);
        this.rucCliente = rucCliente;
    }

    @Override
    public void emitir() {
        log.info("Emitiendo factura legal XML/PDF para el RUC: {}", rucCliente);
    }

    @Override
    public double calcularTotal() {
        double subtotal = total;
        double igv = subtotal * IGV;
        double totalConImpuesto = subtotal + igv;
        log.info("Calculando total (Subtotal: {} + IGV 18%: {}) = {}", subtotal, igv, totalConImpuesto);
        return totalConImpuesto;
    }

    /**
     * Valida que el RUC tenga 11 digitos.
     * @return true si es valido, false en caso contrario.
     */
    public boolean validarRUC() {
        boolean esValido = rucCliente != null && rucCliente.length() == 11;
        log.info("Validando formato de RUC ({}): {}", rucCliente, esValido ? "VALIDA" : "INVALIDA");
        return esValido;
    }
}