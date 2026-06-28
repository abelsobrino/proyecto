package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter //Metodos Getter
@Setter //Metodos Setter
@AllArgsConstructor //Consutructor con parametros
@NoArgsConstructor //Constructor vacio

@Entity
@Table(name="impuesto")
public class Impuesto {
    @Id
    private String tipoImpuesto;
    private double porcentaje;
    private double monto;

    public double calcularMontoImpuesto(double base) {
        this.monto = base * (porcentaje / 100);
        System.out.println("[Impuesto] Calculando " + tipoImpuesto + " (" + porcentaje + "%) sobre " + base + " = Monto: " + this.monto);
        return this.monto;
    }

    public void actualizarPorcentaje(double nuevoPorcentaje) {
        this.porcentaje = nuevoPorcentaje;
        System.out.println("[Impuesto] Tasa de " + tipoImpuesto + " actualizada a: " + nuevoPorcentaje + "%");
    }
}
