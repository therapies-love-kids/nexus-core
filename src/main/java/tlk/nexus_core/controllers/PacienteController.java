package tlk.nexus_core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import tlk.nexus_core.mappers.PacienteMapper;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.models.dtos.PacienteCreateDTO;
import tlk.nexus_core.services.PacienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  @Autowired
  private PacienteService service;

  @Autowired
  private PacienteMapper mapper;

  @PostMapping
  @Operation(description = "Endpoint para adicionar um paciente")
  public ResponseEntity<Object> create(@Valid @RequestBody PacienteCreateDTO dto) {
    try {
      PacienteModel model = service.create(mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.CREATED).body(model);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os pacientes")
  public ResponseEntity<Object> getAll() {
    try {
      List<PacienteModel> pacientes = service.getAll();
      if (pacientes.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum paciente encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
