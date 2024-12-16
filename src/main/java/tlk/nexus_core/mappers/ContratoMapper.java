package tlk.nexus_core.mappers;

import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tlk.nexus_core.models.ContratoModel;
import tlk.nexus_core.models.dtos.ContratoCreateDTO;
import tlk.nexus_core.models.dtos.ContratoUpdateDTO;
import tlk.nexus_core.repositories.PacienteRepository;
import tlk.nexus_core.repositories.RepresentanteRepository;

@Component
@RequiredArgsConstructor
public class ContratoMapper {

  private final PacienteRepository pacienteRepository;
  private final RepresentanteRepository representanteRepository;

  // Mapeamento de ContratoCreateDTO para ContratoModel
  public ContratoModel toModel(@Valid ContratoCreateDTO dto) {
    ContratoModel model = new ContratoModel();
    model.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElseThrow());
    model.setRepresentante(representanteRepository.findById(dto.getRepresentanteId()).orElseThrow());
    model.setConvenio(dto.getConvenio());
    return model;
  }

  // Mapeamento de ContratoUpdateDTO para ContratoModel
  public ContratoModel toModel(@Valid ContratoUpdateDTO dto) {
    ContratoModel model = new ContratoModel();
    model.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElseThrow());
    model.setRepresentante(representanteRepository.findById(dto.getRepresentanteId()).orElseThrow());
    model.setConvenio(dto.getConvenio());
    return model;
  }

}

