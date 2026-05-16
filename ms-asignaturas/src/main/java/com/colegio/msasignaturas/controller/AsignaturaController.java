package com.colegio.msasignaturas.controller;

import com.colegio.msasignaturas.dto.AsignaturaRequestDTO;
import com.colegio.msasignaturas.dto.AsignaturaResponseDTO;
import com.colegio.msasignaturas.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")                                                                                                                                         
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService service; 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public AsignaturaResponseDTO crear(@RequestBody AsignaturaRequestDTO request) {

        return service.crearAsignatura(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK) 
    public List<AsignaturaResponseDTO> listar() {

        return service.listarTodas();
    }
}