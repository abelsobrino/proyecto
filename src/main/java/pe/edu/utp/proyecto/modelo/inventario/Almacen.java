package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Representa un almacen fisico donde se guardan los productos.
 */
@Entity
@Table(name = "almacen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlmacen;

    private String nombre;

    private String direccion;

    private String responsable;

    private BigDecimal capacidadMaxima;
}