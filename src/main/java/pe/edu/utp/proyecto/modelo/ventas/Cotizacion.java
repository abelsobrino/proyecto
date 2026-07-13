package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Representa una cotizacion de venta.
 */
@Entity
@Table(name = "cotizaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cotizacion")
    private int idCotizacion;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "total_estimado", precision = 10, scale = 2)
    private BigDecimal totalEstimado;
}