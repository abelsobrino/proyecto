package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="impuesto")
@Slf4j
public class Impuesto {
    @Id
    private String tipoImpuesto;
    private double porcentaje;
    private double monto;

    public double calcularMontoImpuesto(double base) {
        this.monto = base * (porcentaje / 100);
        log.info("[Impuesto] Calculando {} ({}%) sobre {} = Monto: {}", tipoImpuesto, porcentaje, base, this.monto);
        return this.monto;
    }

    public void actualizarPorcentaje(double nuevoPorcentaje) {
        this.porcentaje = nuevoPorcentaje;
        log.info("[Impuesto] Tasa de {} actualizada a: {}%", tipoImpuesto, nuevoPorcentaje);
    }
}
