package tlk.nexus_core.mappers;

import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.models.dtos.PacienteCreateDTO;
import tlk.nexus_core.models.dtos.PacienteUpdateDTO;
import tlk.nexus_core.utils.CodigoGenerator;

@Component
public class PacienteMapper {

  // Mapeamento de PacienteCreateDTO para PacienteModel
  public PacienteModel toModel(@Valid PacienteCreateDTO dto) {
    PacienteModel model = new PacienteModel();
    model.setCodigo(CodigoGenerator.generate(dto.getUnidade()));
    model.setNome(dto.getNome());
    model.setNomeCurto(dto.getNomeCurto());
    model.setSexo(dto.getSexo());
    model.setDataNascimento(dto.getDataNascimento());
    model.setCertidaoNascimento(dto.getCertidaoNascimento());
    model.setCpf(dto.getCpf());
    model.setConvenio(dto.getConvenio());
    model.setNumeroConvenio(dto.getNumeroConvenio());
    model.setEndereco(dto.getEndereco());
    model.setAnotacoes(dto.getAnotacoes());
    model.setObservacoes(dto.getObservacoes());
    return model;
  }

  // Mapeamento de PacienteUpdateDTO para PacienteModel
  public PacienteModel toModel(@Valid PacienteUpdateDTO dto) {
    PacienteModel model = new PacienteModel();
    model.setAtivo(dto.getAtivo());
    model.setNome(dto.getNome());
    model.setNomeCurto(dto.getNomeCurto());
    model.setSexo(dto.getSexo());
    model.setDataNascimento(dto.getDataNascimento());
    model.setCertidaoNascimento(dto.getCertidaoNascimento());
    model.setCpf(dto.getCpf());
    model.setConvenio(dto.getConvenio());
    model.setNumeroConvenio(dto.getNumeroConvenio());
    model.setEndereco(dto.getEndereco());
    model.setAnotacoes(dto.getAnotacoes());
    model.setObservacoes(dto.getObservacoes());
    return model;
  }

}
