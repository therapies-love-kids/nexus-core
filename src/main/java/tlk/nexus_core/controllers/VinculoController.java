package tlk.nexus_core.controllers;

import java.util.List;

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
import tlk.nexus_core.mappers.VinculoMapper;
import tlk.nexus_core.models.ContratoModel;
import tlk.nexus_core.models.VinculoModel;
import tlk.nexus_core.models.dtos.ContratoCreateDTO;
import tlk.nexus_core.models.dtos.VinculoCreateDTO;
import tlk.nexus_core.models.dtos.VinculoUpdateDTO;
import tlk.nexus_core.services.VinculoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vinculos")
public class VinculoController {

  @Autowired
  private VinculoService service;

  @Autowired
  private VinculoMapper mapper;

  @PostMapping
  @Operation(description = "Endpoint para adicionar um vínculo")
  public ResponseEntity<Object> create(@Valid @RequestBody VinculoCreateDTO dto) {
    try {
      VinculoModel model = service.create(mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.CREATED).body(model);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os vínculos")
  public ResponseEntity<Object> getAll() {
    try {
      List<VinculoModel> vinculos = service.getAll();
      if (vinculos.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum vínculo encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(vinculos);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  @Operation(description = "Endpoint para buscar um vínculo pelo ID")
  public ResponseEntity<Object> getById(Long id) {
    try {
      VinculoModel vinculo = service.getById(id);
      if (vinculo == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vínculo não encontrado");
      }
      return ResponseEntity.status(HttpStatus.OK).body(vinculo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  @Operation(description = "Endpoint para atualizar um vínculo")
  public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody VinculoUpdateDTO dto) {
    try {
      VinculoModel vinculo = service.update(id, mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.OK).body(vinculo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/ativar/{id}")
  @Operation(description = "Endpoint para ativar um vínculo")
  public ResponseEntity<Object> activate(@PathVariable Long id) {
    try {
      VinculoModel vinculo = service.activate(id);
      return ResponseEntity.status(HttpStatus.OK).body(vinculo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/inativar/{id}")
  @Operation(description = "Endpoint para inativar um vínculo")
  public ResponseEntity<Object> inactivate(@PathVariable Long id) {
    try {
      VinculoModel vinculo = service.inactivate(id);
      return ResponseEntity.status(HttpStatus.OK).body(vinculo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}

