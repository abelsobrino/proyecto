package pe.edu.utp.proyecto.service.inventario.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;
import pe.edu.utp.proyecto.repository.inventario.LoteProductoRepository;
import pe.edu.utp.proyecto.service.inventario.LoteProductoService;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoteProductoServiceImpl implements LoteProductoService {

    private final LoteProductoRepository loteProductoRepository;

    @Override
    @Transactional
    public LoteProducto guardarLote(LoteProducto lote) {
        try {
            log.info("Guardando nuevo lote: {}", lote.getCodigoLote());
            return loteProductoRepository.save(lote);
        } catch (Exception e) {
            log.error("Error al guardar lote: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el lote: " + e.getMessage());
        }
    }

    @Override
    public Optional<LoteProducto> obtenerLotePorId(Integer id) {
        try {
            log.debug("Buscando lote con ID: {}", id);
            return loteProductoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar lote: {}", e.getMessage());
            throw new BusinessException("Error al buscar el lote: " + e.getMessage());
        }
    }

    @Override
    public List<LoteProducto> obtenerTodosLosLotes() {
        try {
            log.info("Obteniendo todos los lotes");
            return loteProductoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener lotes: {}", e.getMessage());
            throw new BusinessException("Error al obtener los lotes: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LoteProducto actualizarLote(Integer id, LoteProducto lote) {
        try {
            log.info("Actualizando lote con ID: {}", id);
            LoteProducto existente = loteProductoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Lote no encontrado con ID: " + id));
            existente.setCodigoLote(lote.getCodigoLote());
            existente.setCantidad(lote.getCantidad());
            existente.setFechaIngreso(lote.getFechaIngreso());
            existente.setFechaVencimiento(lote.getFechaVencimiento());
            existente.setProducto(lote.getProducto());
            existente.setAlmacen(lote.getAlmacen());
            return loteProductoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar lote: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el lote: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarLote(Integer id) {
        try {
            log.info("Eliminando lote con ID: {}", id);
            if (!loteProductoRepository.existsById(id)) {
                throw new BusinessException("Lote no encontrado con ID: " + id);
            }
            loteProductoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar lote: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el lote: " + e.getMessage());
        }
    }

    @Override
    public List<LoteProducto> obtenerLotesPorCodigo(String codigoLote) {
        try {
            log.info("Buscando lotes por codigo: {}", codigoLote);
            return loteProductoRepository.findByCodigoLote(codigoLote);
        } catch (Exception e) {
            log.error("Error al buscar lotes por codigo: {}", e.getMessage());
            throw new BusinessException("Error al buscar los lotes: " + e.getMessage());
        }
    }

    @Override
    public List<LoteProducto> obtenerLotesPorFechaVencimiento(Date fechaVencimiento) {
        try {
            log.info("Buscando lotes por fecha de vencimiento: {}", fechaVencimiento);
            return loteProductoRepository.findByFechaVencimiento(fechaVencimiento);
        } catch (Exception e) {
            log.error("Error al buscar lotes por fecha: {}", e.getMessage());
            throw new BusinessException("Error al buscar los lotes: " + e.getMessage());
        }
    }
}