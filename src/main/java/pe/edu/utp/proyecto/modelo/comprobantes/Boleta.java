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
 * Representa una boleta de venta simplificada.
 * Extiende de ComprobanteElectronico y agrega el DNI del cliente.
 */
@Setter
@Getter
@Entity
@Table(name = "boleta")
@Slf4j
public class Boleta extends ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoleta;

    /** DNI del cliente que realiza la compra */
    private String dniCliente;

    public Boleta() {
        super();
    }

    public Boleta(String serie, String numero, LocalDate fechaEmision, double total, String dniCliente) {
        super(serie, numero, fechaEmision, total);
        this.dniCliente = dniCliente;
    }

    @Override
    public void emitir() {
        log.info("Emitiendo boleta de venta simplificada para el DNI: {}", dniCliente);
    }

    @Override
    public double calcularTotal() {
        log.info("Calculando total final de venta al por menor: {}", this.total);
        return this.total;
    }
}