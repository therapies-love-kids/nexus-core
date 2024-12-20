package tlk.nexus_core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import tlk.nexus_core.mappers.RepresentanteMapper;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.models.dtos.RepresentanteCreateDTO;
import tlk.nexus_core.models.dtos.RepresentanteUpdateDTO;
import tlk.nexus_core.services.RepresentanteService;
import tlk.nexus_core.utils.ErrorResponse;
import tlk.nexus_core.utils.SuccessResponse;

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
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new SuccessResponse(model));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os representantes")
  public ResponseEntity<Object> getAll() {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(service.getAll()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/{id}")
  @Operation(description = "Endpoint para buscar um representante pelo ID")
  public ResponseEntity<Object> getById(Long id) {
    try {
      RepresentanteModel representante = service.getById(id);
      if (representante == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Representante nao encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(representante));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/cpf/{cpf}")
  @Operation(description = "Endpoint para buscar um representante pelo CPF")
  public ResponseEntity<Object> getByCpf(@PathVariable String cpf) {
    try {
      RepresentanteModel representante = service.getByCpf(cpf);
      if (representante == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Representante nao encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(representante));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/{id}")
  @Operation(description = "Endpoint para atualizar um representante")
  public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody RepresentanteUpdateDTO dto) {
    try {
      RepresentanteModel representante = service.update(id, mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(representante));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/ativar/{id}")
  @Operation(description = "Endpoint para ativar um representante")
  public ResponseEntity<Object> activate(@PathVariable Long id) {
    try {
      RepresentanteModel representante = service.activate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(representante));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/inativar/{id}")
  @Operation(description = "Endpoint para inativar um representante")
  public ResponseEntity<Object> inactivate(@PathVariable Long id) {
    try {
      RepresentanteModel representante = service.inactivate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(representante));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

}
