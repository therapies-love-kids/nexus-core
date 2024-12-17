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
import tlk.nexus_core.mappers.ContratoMapper;
import tlk.nexus_core.models.ContratoModel;
import tlk.nexus_core.models.dtos.ContratoCreateDTO;
import tlk.nexus_core.services.ContratoService;

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
      return ResponseEntity.status(HttpStatus.CREATED).body(model);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os contratos")
  public ResponseEntity<Object> getAll() {
    try {
      List<ContratoModel> contratos = service.getAll();
      if (contratos.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum contrato encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  @Operation(description = "Endpoint para buscar um contrato pelo ID")
  public ResponseEntity<Object> getById(Long id) {
    try {
      ContratoModel contrato = service.getById(id);
      if (contrato == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(contrato);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
