package com.colegio.msasignaturas.service;

import com.colegio.msasignaturas.dto.AsignaturaRequestDTO;
import com.colegio.msasignaturas.dto.AsignaturaResponseDTO;
import com.colegio.msasignaturas.model.Asignatura;
import com.colegio.msasignaturas.repository.AsignaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor 
public class AsignaturaService {

    private final AsignaturaRepository repository;

    
    public AsignaturaResponseDTO crearAsignatura(AsignaturaRequestDTO request) {
        
        Asignatura nuevaAsignatura = new Asignatura();
        nuevaAsignatura.setNombre(request.getNombre());
        nuevaAsignatura.setDepartamento(request.getDepartamento());

        Asignatura asignaturaGuardada = repository.save(nuevaAsignatura);

        AsignaturaResponseDTO response = new AsignaturaResponseDTO();
        response.setId(asignaturaGuardada.getId());
        response.setNombre(asignaturaGuardada.getNombre());
        response.setDepartamento(asignaturaGuardada.getDepartamento());

        return response;
    }

    public List<AsignaturaResponseDTO> listarTodas() {
        return repository.findAll().stream().map(asignatura -> {
            AsignaturaResponseDTO dto = new AsignaturaResponseDTO();
            dto.setId(asignatura.getId());
            dto.setNombre(asignatura.getNombre());
            dto.setDepartamento(asignatura.getDepartamento());
            return dto;
        }).collect(Collectors.toList());
    }
}