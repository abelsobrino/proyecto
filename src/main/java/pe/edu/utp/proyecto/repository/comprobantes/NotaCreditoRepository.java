package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;

import java.util.List;

/**
 * Repositorio para la entidad NotaCredito.
 * Proporciona metodos CRUD y consultas personalizadas.
 */
@Repository
public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long> {

    /**
     * Busca notas de credito cuyo motivo contenga un texto.
     * @param motivo Texto a buscar en el motivo.
     * @return Lista de notas de credito que coinciden.
     */
    List<NotaCredito> findByMotivoContaining(String motivo);

    /**
     * Busca notas de credito cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de notas de credito con total mayor al especificado.
     */
    List<NotaCredito> findByTotalGreaterThan(double total);
}