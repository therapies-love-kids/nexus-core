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
import tlk.nexus_core.mappers.RepresentanteMapper;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.models.dtos.RepresentanteCreateDTO;
import tlk.nexus_core.services.RepresentanteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/representantes")
public class RepresentanteController {

  @Autowired
  private RepresentanteService service;

  @Autowired
  private RepresentanteMapper mapper;

  @PostMapping
  @Operation(description = "Endpoint para adicionar um representante")
  public ResponseEntity<Object> create(@Valid @RequestBody RepresentanteCreateDTO dto) {
    try {
      RepresentanteModel model = service.create(mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.CREATED).body(model);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os representantes")
  public ResponseEntity<Object> getAll() {
    try {
      List<RepresentanteModel> representantes = service.getAll();
      if (representantes.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum representante encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
