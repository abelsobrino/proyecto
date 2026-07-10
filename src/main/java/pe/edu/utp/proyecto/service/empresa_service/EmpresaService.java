package pe.edu.utp.proyecto.service.empresa_service;

import pe.edu.utp.proyecto.modelo.empresa.Empresa;
import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    Empresa guardarEmpresa(Empresa empresa);
    Optional<Empresa> obtenerEmpresaPorId(String ruc);
    List<Empresa> obtenerTodasLasEmpresas();
    Empresa actualizarEmpresa(String ruc, Empresa empresa);
    void eliminarEmpresa(String ruc);
}