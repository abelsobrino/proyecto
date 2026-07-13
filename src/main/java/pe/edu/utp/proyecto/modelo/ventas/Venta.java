package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.service.patron.comportamiento_state.EstadoFactory;
import pe.edu.utp.proyecto.service.patron.comportamiento_state.EstadoVenta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta realizada a un cliente.
 * Utiliza el patron State para gestionar los estados de la venta.
 */
@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    /** Fecha y hora de la venta en formato LocalDateTime */
    private LocalDateTime fechaVenta;

    /** Estado actual de la venta (PENDIENTE, PROCESANDO, COMPLETADA, CANCELADA) */
    private String estado;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();

    @Transient
    private EstadoVenta estadoActual;

    public Venta(int idVenta, LocalDateTime fechaVenta, String estado, Cliente cliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = BigDecimal.ZERO;
        this.estadoActual = EstadoFactory.getEstado(estado);
    }

    /**
     * Agrega un detalle a la venta y actualiza el total.
     * @param detalle Detalle a agregar.
     */
    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        this.total = this.total.add(detalle.getSubtotal());
    }

    /**
     * Calcula el total de la venta sumando los subtotales de los detalles.
     * @return Total calculado.
     */
    public BigDecimal calcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.total;
    }

    // METODOS DEL PATRON STATE

    /**
     * Cambia el estado de la venta a PROCESANDO si es permitido.
     */
    public void procesar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.procesar(this);
    }

    /**
     * Cambia el estado de la venta a COMPLETADA si es permitido.
     */
    public void completar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.completar(this);
    }

    /**
     * Cambia el estado de la venta a CANCELADA si es permitido.
     */
    public void cancelar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.cancelar(this);
    }

    /**
     * Obtiene el nombre del estado actual.
     * @return Nombre del estado actual.
     */
    public String getEstadoActual() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        return estadoActual.getNombreEstado();
    }

    /**
     * Establece un nuevo estado y actualiza el estadoActual.
     * @param nuevoEstado Nombre del nuevo estado.
     */
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        this.estadoActual = EstadoFactory.getEstado(nuevoEstado);
    }
}