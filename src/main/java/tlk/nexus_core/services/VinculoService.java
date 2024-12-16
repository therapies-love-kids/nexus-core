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
