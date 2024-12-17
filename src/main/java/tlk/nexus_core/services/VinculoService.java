package tlk.nexus_core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.models.VinculoModel;
import tlk.nexus_core.repositories.VinculoRepository;

@Service
public class VinculoService {
  
  @Autowired
  private VinculoRepository repository;

  public VinculoModel create(@Valid VinculoModel vinculo) {
    vinculo = validateBusinessLogic(vinculo);
    return repository.save(vinculo);
  }

  public List<VinculoModel> getAll() {
    return repository.findAll();
  }

  public VinculoModel getById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public VinculoModel update(Long id, @Valid VinculoModel vinculoUpdate) {
    VinculoModel vinculo = repository.findById(id).orElse(null);
    if (vinculo == null) {
      throw new IllegalArgumentException("Vinculo não encontrado.");
    }
    vinculoUpdate = validateBusinessLogic(vinculoUpdate);
    updateDataDB(vinculo, vinculoUpdate);
    return repository.save(vinculo);
  }

  public VinculoModel activate(Long id) {
    VinculoModel vinculo = repository.findById(id).orElse(null);
    if (vinculo == null) {
      throw new IllegalArgumentException("Vinculo não encontrado.");
    }
    vinculo.setAtivo(true);
    return repository.save(vinculo);
  }

  public VinculoModel inactivate(Long id) {
    VinculoModel vinculo = repository.findById(id).orElse(null);
    if (vinculo == null) {
      throw new IllegalArgumentException("Vinculo não encontrado.");
    }
    vinculo.setAtivo(false);
    return repository.save(vinculo);
  }

  public void updateDataDB(VinculoModel vinculo, VinculoModel vinculoUpdate) {
    if (vinculoUpdate.getAtivo() != null) {
      vinculo.setAtivo(vinculoUpdate.getAtivo());
    }
    if (vinculoUpdate.getPaciente() != null) {
      vinculo.setPaciente(vinculoUpdate.getPaciente());
    }
    if (vinculoUpdate.getRepresentante() != null) {
      vinculo.setRepresentante(vinculoUpdate.getRepresentante());
    }
    if (vinculoUpdate.getTipo() != null) {
      vinculo.setTipo(vinculoUpdate.getTipo());
    }
  }

  public VinculoModel validateBusinessLogic(VinculoModel vinculo) {
    PacienteModel paciente = vinculo.getPaciente();
    RepresentanteModel representante = vinculo.getRepresentante();
    String tipo = vinculo.getTipo();

    // Validação dos campos obrigatórios
    if (paciente == null || representante == null || tipo == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    return vinculo;
  }

}
