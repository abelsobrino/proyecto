package pe.edu.utp.proyecto.modelo.empresa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa los datos de la empresa.
 */
@Getter
@Setter
@Entity
@Table(name = "empresa")
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    private String ruc;

    private String razonSocial;

    private String direccion;

    private String telefono;
}