package tlk.nexus_core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tlk.nexus_core.models.ContratoModel;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.repositories.ContratoRepository;

@Service
public class ContratoService {
  
  @Autowired
  private ContratoRepository repository;

  public ContratoModel create(@Valid ContratoModel contrato) {
    contrato = validateBusinessLogic(contrato);
    return repository.save(contrato);
  }

  public List<ContratoModel> getAll() {
    return repository.findAll();
  }

  public ContratoModel getById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public ContratoModel update(Long id, @Valid ContratoModel contratoUpdate) {
    ContratoModel contrato = repository.findById(id).orElse(null);
    if (contrato == null) {
      throw new IllegalArgumentException("Contrato não encontrado.");
    }
    contratoUpdate = validateBusinessLogic(contratoUpdate);
    updateDataDB(contrato, contratoUpdate);
    return repository.save(contrato);
  }

  public ContratoModel activate(Long id) {
    ContratoModel contrato = repository.findById(id).orElse(null);
    if (contrato == null) {
      throw new IllegalArgumentException("Contrato não encontrado.");
    }
    contrato.setAtivo(true);
    return repository.save(contrato);
  }

  public ContratoModel inactivate(Long id) {
    ContratoModel contrato = repository.findById(id).orElse(null);
    if (contrato == null) {
      throw new IllegalArgumentException("Contrato não encontrado.");
    }
    contrato.setAtivo(false);
    return repository.save(contrato);
  }

  public void updateDataDB(ContratoModel contrato, ContratoModel contratoUpdate) {
    if (contratoUpdate.getAtivo() != null) {
      contrato.setAtivo(contratoUpdate.getAtivo());
    }
    if (contratoUpdate.getPaciente() != null) {
      contrato.setPaciente(contratoUpdate.getPaciente());
    }
    if (contratoUpdate.getRepresentante() != null) {
      contrato.setRepresentante(contratoUpdate.getRepresentante());
    }
    if (contratoUpdate.getConvenio() != null) {
      contrato.setConvenio(contratoUpdate.getConvenio());
    }
  }

  public ContratoModel validateBusinessLogic(ContratoModel contrato) {
    PacienteModel paciente = contrato.getPaciente();
    RepresentanteModel representante = contrato.getRepresentante();
    Boolean convenio = contrato.getConvenio();

    // Validação dos campos obrigatórios
    if (paciente == null || representante == null || convenio == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    return contrato;
  }

}
