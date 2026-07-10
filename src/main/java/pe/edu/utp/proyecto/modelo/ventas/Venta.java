package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.service.patron.comportamiento_state.EstadoFactory;
import pe.edu.utp.proyecto.service.patron.comportamiento_state.EstadoVenta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    private String estado;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();

    @Transient
    private EstadoVenta estadoActual;

    public Venta(int idVenta, Date fechaVenta, String estado, Cliente cliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = BigDecimal.ZERO;
        this.estadoActual = EstadoFactory.getEstado(estado);
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        this.total = this.total.add(detalle.getSubtotal());
    }

    public BigDecimal calcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.total;
    }

    // METODOS DEL PATRON STATE
    public void procesar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.procesar(this);
    }

    public void completar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.completar(this);
    }

    public void cancelar() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        estadoActual.cancelar(this);
    }

    public String getEstadoActual() {
        if (estadoActual == null) {
            estadoActual = EstadoFactory.getEstado(this.estado);
        }
        return estadoActual.getNombreEstado();
    }

    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        this.estadoActual = EstadoFactory.getEstado(nuevoEstado);
    }
}