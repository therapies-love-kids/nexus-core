package tlk.nexus_core.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tlk.nexus_core.models.PacienteModel;
import tlk.nexus_core.repositories.PacienteRepository;

@Service
public class PacienteService {
  
  @Autowired
  private PacienteRepository repository;

  public PacienteModel create(@Valid PacienteModel paciente) {
    paciente = validateBusinessLogic(paciente);
    return repository.save(paciente);
  }

  public List<PacienteModel> getAll() {
    return repository.findAll();
  }

  // Validação de regras de negócio
  public PacienteModel validateBusinessLogic(PacienteModel paciente) {
    String nome = paciente.getNome();
    String nomeCurto = paciente.getNomeCurto();
    String sexo = paciente.getSexo();
    LocalDate dataNascimento = paciente.getDataNascimento();
    String localNascimento = paciente.getLocalNascimento();
    String certidaoNascimento = paciente.getCertidaoNascimento();
    String cpf = paciente.getCpf();
    String convenio = paciente.getConvenio();
    String numeroConvenio = paciente.getNumeroConvenio();
    String cep = paciente.getCep();
    String endereco = paciente.getEndereco();
    String anotacoes = paciente.getAnotacoes();
    String observacoes = paciente.getObservacoes();

    // Validação dos campos obrigatórios
    if (nome == null || sexo == null || nome.isEmpty() || sexo.isEmpty() || dataNascimento == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    // Validação dos campos únicos
    if (repository.findByNomeCurto(nomeCurto) != null && repository.findByNomeCurto(nomeCurto).size() > 0) {
      throw new IllegalArgumentException("Nome curto já cadastrado.");
    } else if (repository.findByCertidaoNascimento(certidaoNascimento) != null && repository.findByCertidaoNascimento(certidaoNascimento).size() > 0) {
      throw new IllegalArgumentException("Certidão de nascimento já cadastrada.");
    } else if (repository.findByCpf(cpf) != null && repository.findByCpf(cpf).size() > 0) {
      throw new IllegalArgumentException("CPF já cadastrado.");
    }

    // Validação dos tamanhos dos campos
    if (nome.length() > 64) {
      throw new IllegalArgumentException("O nome do paciente deve ter no máximo 64 caracteres.");
    } else if (nomeCurto != null && nomeCurto.length() > 32) {
      throw new IllegalArgumentException("O nome curto do paciente deve ter no máximo 32 caracteres.");
    } else if (sexo.length() > 16) {
      throw new IllegalArgumentException("O sexo do paciente deve ter no máximo 16 caracteres.");
    } else if (localNascimento != null && localNascimento.length() > 64) {
      throw new IllegalArgumentException("O local de nascimento do paciente deve ter no máximo 64 caracteres.");
    } else if (certidaoNascimento != null && certidaoNascimento.length() > 32) {
      throw new IllegalArgumentException("O número da certidão de nascimento do paciente deve ter no máximo 32 caracteres.");
    } else if (cpf != null && cpf.length() != 11) {
      throw new IllegalArgumentException("O CPF do paciente deve 11 caracteres.");
    } else if (convenio != null && convenio.length() > 32) {
      throw new IllegalArgumentException("O convênio do paciente deve ter no máximo 32 caracteres.");
    } else if (numeroConvenio != null && numeroConvenio.length() > 32) {
      throw new IllegalArgumentException("O número do convênio do paciente deve ter no máximo 32 caracteres.");
    } else if (cep != null && cep.length() != 8) {
      throw new IllegalArgumentException("O CEP do paciente deve ter 8 caracteres.");
    } else if (endereco != null && endereco.length() > 128) {
      throw new IllegalArgumentException("O endereço do paciente deve ter no máximo 128 caracteres.");
    } else if (anotacoes != null && anotacoes.length() > 256) {
      throw new IllegalArgumentException("As anotações do paciente devem ter no máximo 256 caracteres.");
    } else if (observacoes != null && observacoes.length() > 256) {
      throw new IllegalArgumentException("As observações do paciente devem ter no máximo 256 caracteres.");
    }

    // Validação do campo "sexo"
    if (sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("m")) {
      paciente.setSexo("Masculino");
    } else if (sexo.toLowerCase().equals("feminino") || sexo.toLowerCase().equals("f")) {
      paciente.setSexo("Feminino");
    } else {
      throw new IllegalArgumentException("Sexo inválido. Deve ser 'Masculino' ou 'Feminino'.");
    }

    // Validação do campo "dataNascimento"
    if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("A data de nascimento do paciente deve ser anterior ou igual à data atual.");
    }

    return paciente;
  }

}
