package pe.edu.utp.proyecto.modelo.comprobantes;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@Slf4j
public abstract class ComprobanteElectronico {

    @Column(nullable = false)
    protected String serie;

    @Column(nullable = false)
    protected String numero;

    @Temporal(TemporalType.DATE)
    protected Date fechaEmision;

    protected double total;

    protected ComprobanteElectronico() {
    }

    protected ComprobanteElectronico(String serie, String numero, Date fechaEmision, double total) {
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    public abstract void emitir();

    public abstract double calcularTotal();

    public void cambiarEstado(String nuevoEstado) {
        log.info("[Comprobante] Estado cambiado a: {}", nuevoEstado);
    }
}