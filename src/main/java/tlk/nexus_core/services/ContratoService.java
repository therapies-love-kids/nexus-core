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
