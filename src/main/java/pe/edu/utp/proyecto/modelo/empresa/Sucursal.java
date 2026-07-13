package pe.edu.utp.proyecto.modelo.empresa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una sucursal de la empresa.
 */
@Entity
@Table(name = "sucursal")
@Getter
@Setter
public class Sucursal {

    @Id
    private int idSucursal;

    private String nombre;

    private String direccion;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String nombre, String direccion) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
    }
}