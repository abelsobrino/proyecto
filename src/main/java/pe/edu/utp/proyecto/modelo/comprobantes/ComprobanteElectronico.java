package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Clase base abstracta para todos los comprobantes electronicos.
 * Define los atributos comunes: serie, numero, fecha de emision y total.
 */
@Getter
@Setter
@MappedSuperclass
@Slf4j
public abstract class ComprobanteElectronico {

    @Column(nullable = false)
    protected String serie;

    @Column(nullable = false)
    protected String numero;

    /** Fecha de emision del comprobante en formato LocalDate */
    protected LocalDate fechaEmision;

    protected double total;

    protected ComprobanteElectronico() {
    }

    protected ComprobanteElectronico(String serie, String numero, LocalDate fechaEmision, double total) {
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    /**
     * Emite el comprobante electronico.
     * Cada tipo de comprobante implementa su propia logica de emision.
     */
    public abstract void emitir();

    /**
     * Calcula el total del comprobante.
     * Cada tipo de comprobante implementa su propia logica de calculo.
     */
    public abstract double calcularTotal();

    /**
     * Cambia el estado del comprobante.
     * @param nuevoEstado Estado al que se desea cambiar.
     */
    public void cambiarEstado(String nuevoEstado) {
        log.info("Estado cambiado a: {}", nuevoEstado);
    }
}