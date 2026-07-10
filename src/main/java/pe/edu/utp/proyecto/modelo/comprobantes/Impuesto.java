package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "impuesto")
@Slf4j
public class Impuesto {
    @Id
    private String tipoImpuesto;

    private BigDecimal porcentaje;

    private BigDecimal monto;

    public BigDecimal calcularMontoImpuesto(BigDecimal base) {
        this.monto = base.multiply(porcentaje.divide(BigDecimal.valueOf(100)));
        log.info("Calculando {} ({}%) sobre {} = Monto: {}", tipoImpuesto, porcentaje, base, this.monto);
        return this.monto;
    }

    public void actualizarPorcentaje(BigDecimal nuevoPorcentaje) {
        this.porcentaje = nuevoPorcentaje;
        log.info("Tasa de {} actualizada a: {}%", tipoImpuesto, nuevoPorcentaje);
    }
}