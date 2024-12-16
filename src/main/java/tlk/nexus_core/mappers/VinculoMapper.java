package tlk.nexus_core.mappers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tlk.nexus_core.models.VinculoModel;
import tlk.nexus_core.models.dtos.VinculoCreateDTO;
import tlk.nexus_core.models.dtos.VinculoUpdateDTO;
import tlk.nexus_core.repositories.PacienteRepository;
import tlk.nexus_core.repositories.RepresentanteRepository;

@Component
@RequiredArgsConstructor
public class VinculoMapper {

  private final PacienteRepository pacienteRepository;
  private final RepresentanteRepository representanteRepository;

  // Mapeamento de VinculoCreateDTO para VinculoModel
  public VinculoModel toModel(@Valid VinculoCreateDTO dto) {
    VinculoModel model = new VinculoModel();
    model.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElseThrow());
    model.setRepresentante(representanteRepository.findById(dto.getRepresentanteId()).orElseThrow());
    model.setTipo(dto.getTipo());
    return model;
  }

  // Mapeamento de VinculoUpdateDTO para VinculoModel
  public VinculoModel toModel(@Valid VinculoUpdateDTO dto) {
    VinculoModel model = new VinculoModel();
    model.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElseThrow());
    model.setRepresentante(representanteRepository.findById(dto.getRepresentanteId()).orElseThrow());
    model.setTipo(dto.getTipo());
    return model;
  }

}

