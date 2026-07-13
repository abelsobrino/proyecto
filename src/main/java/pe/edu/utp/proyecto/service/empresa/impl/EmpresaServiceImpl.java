package pe.edu.utp.proyecto.service.empresa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.empresa.Empresa;
import pe.edu.utp.proyecto.repository.empresa.EmpresaRepository;
import pe.edu.utp.proyecto.service.empresa.EmpresaService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de empresas.
 * Contiene la logica de negocio para la gestion de empresas.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public Empresa guardarEmpresa(Empresa empresa) {
        try {
            log.info("Guardando nueva empresa: {}", empresa.getRazonSocial());
            return empresaRepository.save(empresa);
        } catch (Exception e) {
            log.error("Error al guardar empresa: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la empresa: " + e.getMessage());
        }
    }

    @Override
    public Optional<Empresa> obtenerEmpresaPorId(String ruc) {
        try {
            log.debug("Buscando empresa con RUC: {}", ruc);
            return empresaRepository.findById(ruc);
        } catch (Exception e) {
            log.error("Error al buscar empresa: {}", e.getMessage());
            throw new BusinessException("Error al buscar la empresa: " + e.getMessage());
        }
    }

    @Override
    public List<Empresa> obtenerTodasLasEmpresas() {
        try {
            log.info("Obteniendo todas las empresas");
            return empresaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener empresas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las empresas: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Empresa actualizarEmpresa(String ruc, Empresa empresa) {
        try {
            log.info("Actualizando empresa con RUC: {}", ruc);
            Empresa existente = empresaRepository.findById(ruc)
                    .orElseThrow(() -> new BusinessException("Empresa no encontrada con RUC: " + ruc));
            existente.setRazonSocial(empresa.getRazonSocial());
            existente.setDireccion(empresa.getDireccion());
            existente.setTelefono(empresa.getTelefono());
            return empresaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar empresa: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la empresa: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarEmpresa(String ruc) {
        try {
            log.info("Eliminando empresa con RUC: {}", ruc);
            if (!empresaRepository.existsById(ruc)) {
                throw new BusinessException("Empresa no encontrada con RUC: " + ruc);
            }
            empresaRepository.deleteById(ruc);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar empresa: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la empresa: " + e.getMessage());
        }
    }

    @Override
    public Empresa buscarPorRuc(String ruc) {
        try {
            log.info("Buscando empresa por RUC: {}", ruc);
            return empresaRepository.findByRuc(ruc);
        } catch (Exception e) {
            log.error("Error al buscar empresa por RUC: {}", e.getMessage());
            throw new BusinessException("Error al buscar la empresa: " + e.getMessage());
        }
    }

    @Override
    public List<Empresa> buscarPorRazonSocialContaining(String razonSocial) {
        try {
            log.info("Buscando empresas por razon social que contenga: {}", razonSocial);
            return empresaRepository.findByRazonSocialContaining(razonSocial);
        } catch (Exception e) {
            log.error("Error al buscar empresas por razon social: {}", e.getMessage());
            throw new BusinessException("Error al buscar empresas: " + e.getMessage());
        }
    }
}