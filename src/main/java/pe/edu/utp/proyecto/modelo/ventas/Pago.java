package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Representa un pago realizado por una venta.
 */
@Entity
@Table(name = "pago")
@Getter
@Setter
public class Pago {

    @Id
    private int idPago;

    private BigDecimal monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;

    public Pago() {
    }

    public Pago(int idPago, BigDecimal monto, Date fechaPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }
}