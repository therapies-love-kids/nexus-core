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
import tlk.nexus_core.mappers.PacienteMapper;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.models.dtos.PacienteCreateDTO;
import tlk.nexus_core.models.dtos.PacienteUpdateDTO;
import tlk.nexus_core.services.PacienteService;
import tlk.nexus_core.utils.ErrorResponse;
import tlk.nexus_core.utils.SuccessResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "${api.version}/pacientes")
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
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new SuccessResponse(model));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para buscar todos os pacientes")
  public ResponseEntity<Object> getAll() {
    try {
      List<PacienteModel> pacientes = service.getAll();
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(pacientes));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/{id}")
  @Operation(description = "Endpoint para buscar um paciente pelo ID")
  public ResponseEntity<Object> getById(Long id) {
    try {
      PacienteModel paciente = service.getById(id);
      if (paciente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Paciente não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/codigo/{codigo}")
  @Operation(description = "Endpoint para buscar um paciente pelo codigo")
  public ResponseEntity<Object> getByCodigo(String codigo) {
    try {
      PacienteModel paciente = service.getByCodigo(codigo);
      if (paciente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Paciente não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/nome/{nome}")
  @Operation(description = "Endpoint para buscar pacientes pelo nome")
  public ResponseEntity<Object> getByNome(String nome) {
    try {
      List<PacienteModel> pacientes = service.getByNome(nome);
      if (pacientes.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Nenhum paciente encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(pacientes));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/nomecurto/{nomeCurto}")
  @Operation(description = "Endpoint para buscar paciente pelo nome curto")
  public ResponseEntity<Object> getByNomeCurto(String nomeCurto) {
    try {
      PacienteModel paciente = service.getByNomeCurto(nomeCurto);
      if (paciente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Paciente não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/certidao/{certidaoNascimento}")
  @Operation(description = "Endpoint para buscar paciente pela certidao de nascimento")
  public ResponseEntity<Object> getByCertidaoNascimento(String certidaoNascimento) {
    try {
      PacienteModel paciente = service.getByCertidaoNascimento(certidaoNascimento);
      if (paciente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Paciente não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/cpf/{cpf}")
  @Operation(description = "Endpoint para buscar paciente pelo CPF")
  public ResponseEntity<Object> getByCpf(String cpf) {
    try {
      PacienteModel paciente = service.getByCpf(cpf);
      if (paciente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Paciente não encontrado"));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/{id}")
  @Operation(description = "Endpoint para atualizar um paciente")
  public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PacienteUpdateDTO dto) {
    try {
      PacienteModel paciente = service.update(id, mapper.toModel(dto));
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/ativar/{id}")
  @Operation(description = "Endpoint para ativar um paciente")
  public ResponseEntity<Object> activate(@PathVariable Long id) {
    try {
      PacienteModel paciente = service.activate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

  @PutMapping("/inativar/{id}")
  @Operation(description = "Endpoint para inativar um paciente")
  public ResponseEntity<Object> inactivate(@PathVariable Long id) {
    try {
      PacienteModel paciente = service.inactivate(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new SuccessResponse(paciente));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage()));
    }
  }

}
