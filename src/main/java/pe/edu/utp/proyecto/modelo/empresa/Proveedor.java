package pe.edu.utp.proyecto.modelo.empresa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un proveedor de la empresa.
 */
@Entity
@Table(name = "proveedor")
@Getter
@Setter
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;

    private String razonSocial;

    private String ruc;

    private String telefono;

    public Proveedor(String razonSocial, String ruc, String telefono) {
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.telefono = telefono;
    }
}