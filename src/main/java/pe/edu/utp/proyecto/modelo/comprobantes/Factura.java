package pe.edu.utp.proyecto.modelo.comprobantes;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Setter
@Getter

@Entity
@Table(name = "factura")
public class Factura extends ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    private String rucCliente;

    // Constructor vacío
    public Factura() {
        super();
    }

    // Constructor con parámetros (llama a super)
    public Factura(String serie, String numero, Date fechaEmision, double total, String rucCliente) {
        super(serie, numero, fechaEmision, total);
        this.rucCliente = rucCliente;
    }

    @Override
    public void emitir() {
        System.out.println("[Factura] Emitiendo factura legal XML/PDF para el RUC: " + rucCliente);
    }

    @Override
    public double calcularTotal() {
        double subtotal = total;
        double igv = subtotal * 0.18;
        double totalConImpuesto = subtotal + igv;
        System.out.println("[Factura] Calculando total (Subtotal: " + subtotal + " + IGV 18%: " + igv + ") = " + totalConImpuesto);
        return totalConImpuesto;
    }

    public boolean validarRUC() {
        boolean esValido = rucCliente != null && rucCliente.length() == 11;
        System.out.println("[Factura] Validando formato de RUC (" + rucCliente + "): " + (esValido ? "VÁLIDO" : "INVÁLIDO"));
        return esValido;
    }
}
