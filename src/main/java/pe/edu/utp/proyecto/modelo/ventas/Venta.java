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
 *
 * @author Sistema de Ventas UTP
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

    private LocalDateTime fechaVenta;

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
        this.estadoActual = EstadoFactory.getEstado(estado, this);
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

    // =============================================
    // METODOS DEL PATRON STATE
    // =============================================

    /**
     * Cambia el estado de la venta a PROCESANDO si es permitido.
     */
    public void procesar() {
        estadoActual.procesar();
    }

    /**
     * Cambia el estado de la venta a COMPLETADA si es permitido.
     */
    public void completar() {
        estadoActual.completar();
    }

    /**
     * Cambia el estado de la venta a CANCELADA si es permitido.
     */
    public void cancelar() {
        estadoActual.cancelar();
    }

    /**
     * Cambia el estado actual de la venta.
     * @param nuevoEstado Nuevo estado a establecer.
     */
    public void cambiarEstado(EstadoVenta nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.estado = nuevoEstado.getNombreEstado();
        this.estadoActual.onEnterState();
    }

    /**
     * Establece un nuevo estado por nombre y actualiza el estadoActual.
     * @param nuevoEstado Nombre del nuevo estado.
     */
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        this.estadoActual = EstadoFactory.getEstado(nuevoEstado, this);
        this.estadoActual.onEnterState();
    }

    /**
     * Obtiene el nombre del estado actual.
     * @return Nombre del estado actual.
     */
    public String getEstadoActual() {
        return estadoActual.getNombreEstado();
    }

    @Override
    public String toString() {
        return "Venta #" + idVenta;
    }
}