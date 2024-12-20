package tlk.nexus_core.mappers;

import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.models.dtos.RepresentanteCreateDTO;
import tlk.nexus_core.models.dtos.RepresentanteUpdateDTO;

@Component
public class RepresentanteMapper {

  // Mapeamento de RepresentanteCreateDTO para RepresentanteModel
  public RepresentanteModel toModel(@Valid RepresentanteCreateDTO dto) {
    RepresentanteModel model = new RepresentanteModel();
    model.setNome(dto.getNome());
    model.setEstadoCivil(dto.getEstadoCivil());
    model.setCpf(dto.getCpf());
    model.setRg(dto.getRg());
    model.setEndereco(dto.getEndereco());
    model.setContatos(dto.getContatos());
    return model;
  }

  // Mapeamento de RepresentanteUpdateDTO para RepresentanteModel
  public RepresentanteModel toModel(@Valid RepresentanteUpdateDTO dto) {
    RepresentanteModel model = new RepresentanteModel();
    model.setAtivo(dto.getAtivo());
    model.setNome(dto.getNome());
    model.setEstadoCivil(dto.getEstadoCivil());
    model.setCpf(dto.getCpf());
    model.setRg(dto.getRg());
    model.setEndereco(dto.getEndereco());
    model.setContatos(dto.getContatos());
    return model;
  }

}
