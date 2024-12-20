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
import tlk.nexus_core.mappers.ContratoMapper;
import tlk.nexus_core.models.ContratoModel;
import tlk.nexus_core.models.dtos.ContratoCreateDTO;
import tlk.nexus_core.services.ContratoService;
import tlk.nexus_core.utils.ErrorResponse;
import tlk.nexus_core.utils.SuccessResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/contratos")
public class ContratoController {

  @Autowired
  private ContratoService service;

  @Autowired
  private ContratoMapper mapper;

  @PostMapping
  @Operation(description = "Endpoint para adicionar um contrato")
  public ResponseEntity<Object> create(@Valid @RequestBody ContratoCreateDTO dto) {
    try {
      ContratoModel model = service.create(mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new SuccessResponse(model));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os contratos")
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
  @Operation(description = "Endpoint para buscar um contrato pelo ID")
  public ResponseEntity<Object> getById(Long id) {
    try {
      ContratoModel contrato = service.getById(id);
      if (contrato == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Contrato não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(contrato));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/{id}")
  @Operation(description = "Endpoint para atualizar um contrato")
  public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ContratoCreateDTO dto) {
    try {
      ContratoModel contrato = service.update(id, mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(contrato));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/ativar/{id}")
  @Operation(description = "Endpoint para ativar um contrato")
  public ResponseEntity<Object> activate(@PathVariable Long id) {
    try {
      ContratoModel contrato = service.activate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(contrato));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/inativar/{id}")
  @Operation(description = "Endpoint para inativar um contrato")
  public ResponseEntity<Object> inactivate(@PathVariable Long id) {
    try {
      ContratoModel contrato = service.inactivate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(contrato));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

}

