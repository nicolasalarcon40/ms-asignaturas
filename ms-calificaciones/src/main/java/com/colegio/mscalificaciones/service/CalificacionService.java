package com.colegio.mscalificaciones.service;

import com.colegio.mscalificaciones.client.AsignaturaClient;
import com.colegio.mscalificaciones.client.EstudianteClient;
import com.colegio.mscalificaciones.dto.CalificacionRequestDTO;
import com.colegio.mscalificaciones.dto.CalificacionResponseDTO;
import com.colegio.mscalificaciones.model.Calificacion;
import com.colegio.mscalificaciones.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository repository;
    private final EstudianteClient estudianteClient;
    private final AsignaturaClient asignaturaClient;

    public CalificacionResponseDTO crear(CalificacionRequestDTO request) {
        estudianteClient.obtenerPorRut(request.getRutEstudiante());
        asignaturaClient.obtenerPorId(request.getIdAsignatura());

        Calificacion entidad = new Calificacion();
        entidad.setNota(request.getNota());
        entidad.setFecha(request.getFecha());
        entidad.setRutEstudiante(request.getRutEstudiante());
        entidad.setIdAsignatura(request.getIdAsignatura());

        Calificacion guardada = repository.save(entidad);
        return mapearDTO(guardada);
    }

    public List<CalificacionResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::mapearDTO)
                .collect(Collectors.toList());
    }

    public CalificacionResponseDTO actualizar(Long id, CalificacionRequestDTO request) {
        Calificacion entidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no encontrado: " + id));

        estudianteClient.obtenerPorRut(request.getRutEstudiante());
        asignaturaClient.obtenerPorId(request.getIdAsignatura());

        entidad.setNota(request.getNota());
        entidad.setFecha(request.getFecha());
        entidad.setRutEstudiante(request.getRutEstudiante());
        entidad.setIdAsignatura(request.getIdAsignatura());

        Calificacion actualizada = repository.save(entidad);
        return mapearDTO(actualizada);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ID no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private CalificacionResponseDTO mapearDTO(Calificacion entidad) {
        CalificacionResponseDTO dto = new CalificacionResponseDTO();
        dto.setId(entidad.getId());
        dto.setNota(entidad.getNota());
        dto.setFecha(entidad.getFecha());
        dto.setRutEstudiante(entidad.getRutEstudiante());
        dto.setIdAsignatura(entidad.getIdAsignatura());
        return dto;
    }
}