package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "lote_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;

    private String codigoLote;
    private int cantidad;
    private Date fechaIngreso;
    private Date fechaVencimiento;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Almacen almacen;

    public boolean estaVencido() {
        return new Date().after(this.fechaVencimiento);
    }
}