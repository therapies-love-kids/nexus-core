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

  /* CREATE */

  public PacienteModel create(@Valid PacienteModel paciente) {
    paciente = validateCreateBusinessLogic(paciente);
    return repository.save(paciente);
  }

  /* READ */

  public List<PacienteModel> getAll() {
    return repository.findAll();
  }

  public PacienteModel getById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public PacienteModel getByCodigo(String codigo) {
    return repository.findByCodigoIgnoreCase(codigo);
  }

  public List<PacienteModel> getByNome(String nome) {
    return repository.findByNomeContainingIgnoreCase(nome);
  }

  public PacienteModel getByNomeCurto(String nomeCurto) {
    return repository.findByNomeCurtoIgnoreCase(nomeCurto);
  }

  public PacienteModel getByCertidaoNascimento(String certidaoNascimento) {
    return repository.findByCertidaoNascimento(certidaoNascimento);
  }

  public PacienteModel getByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

  /* UPDATE */

  public PacienteModel update(Long id, @Valid PacienteModel pacienteUpdate) {
    PacienteModel paciente = repository.findById(id).orElse(null);
    if (paciente == null) {
      throw new IllegalArgumentException("Paciente não encontrado.");
    }
    pacienteUpdate = validateUpdateBusinessLogic(paciente, pacienteUpdate);
    updateDataDB(paciente, pacienteUpdate);
    return repository.save(paciente);
  }

  public PacienteModel activate(Long id) {
    PacienteModel paciente = repository.findById(id).orElse(null);
    if (paciente == null) {
      throw new IllegalArgumentException("Paciente não encontrado.");
    }
    paciente.setAtivo(true);
    return repository.save(paciente);
  }

  public PacienteModel inactivate(Long id) {
    PacienteModel paciente = repository.findById(id).orElse(null);
    if (paciente == null) {
      throw new IllegalArgumentException("Paciente não encontrado.");
    }
    paciente.setAtivo(false);
    return repository.save(paciente);
  }

  public void updateDataDB(@Valid PacienteModel paciente, @Valid PacienteModel pacienteUpdate) {
    if (pacienteUpdate.getAtivo() != null) {
      paciente.setAtivo(pacienteUpdate.getAtivo());
    }
    if (pacienteUpdate.getNome() != null) {
      paciente.setNome(pacienteUpdate.getNome());
    }
    if (pacienteUpdate.getNomeCurto() != null) {
      paciente.setNomeCurto(pacienteUpdate.getNomeCurto());
    }
    if (pacienteUpdate.getSexo() != null) {
      paciente.setSexo(pacienteUpdate.getSexo());
    }
    if (pacienteUpdate.getDataNascimento() != null) {
      paciente.setDataNascimento(pacienteUpdate.getDataNascimento());
    }
    if (pacienteUpdate.getLocalNascimento() != null) {
      paciente.setLocalNascimento(pacienteUpdate.getLocalNascimento());
    }
    if (pacienteUpdate.getCertidaoNascimento() != null) {
      paciente.setCertidaoNascimento(pacienteUpdate.getCertidaoNascimento());
    }
    if (pacienteUpdate.getCpf() != null) {
      paciente.setCpf(pacienteUpdate.getCpf());
    }
    if (pacienteUpdate.getConvenio() != null) {
      paciente.setConvenio(pacienteUpdate.getConvenio());
    }
    if (pacienteUpdate.getNumeroConvenio() != null) {
      paciente.setNumeroConvenio(pacienteUpdate.getNumeroConvenio());
    }
    if (pacienteUpdate.getCep() != null) {
      paciente.setCep(pacienteUpdate.getCep());
    }
    if (pacienteUpdate.getEndereco() != null) {
      paciente.setEndereco(pacienteUpdate.getEndereco());
    }
    if (pacienteUpdate.getAnotacoes() != null) {
      paciente.setAnotacoes(pacienteUpdate.getAnotacoes());
    }
    if (pacienteUpdate.getObservacoes() != null) {
      paciente.setObservacoes(pacienteUpdate.getObservacoes());
    }
  }

  public PacienteModel validateCreateBusinessLogic(PacienteModel paciente) {
    String codigo = paciente.getCodigo();
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

    /* Validação do preenchimento dos campos obrigatórios */

    if (codigo.length() == 13) {
      throw new IllegalArgumentException("Unidade não foi preenchida.");
    } else if (nome == null || nome.isEmpty()) {
      throw new IllegalArgumentException("Nome não foi preenchido.");
    } else if (sexo == null || sexo.isEmpty()) {
      throw new IllegalArgumentException("Sexo não foi preenchido.");
    } else if (dataNascimento == null) {
      throw new IllegalArgumentException("Data de nascimento não foi preenchida.");
    }

    /* Validação dos tamanhos dos campos */

    if (codigo.length() != 16) {
      throw new IllegalArgumentException("A unidade deve ter 3 caracteres.");
    } else if (nome.length() > 64) {
      throw new IllegalArgumentException("O nome deve ter no máximo 64 caracteres.");
    } else if (nomeCurto != null && nomeCurto.length() > 32) {
      throw new IllegalArgumentException("O nome curto deve ter no máximo 32 caracteres.");
    } else if (sexo.length() > 16) {
      throw new IllegalArgumentException("O sexo deve ter no máximo 16 caracteres.");
    } else if (localNascimento != null && localNascimento.length() > 64) {
      throw new IllegalArgumentException("O local de nascimento deve ter no máximo 64 caracteres.");
    } else if (certidaoNascimento != null && certidaoNascimento.length() > 32) {
      throw new IllegalArgumentException("O número da certidão de nascimento deve ter no máximo 32 caracteres.");
    } else if (cpf != null && cpf.length() != 11) {
      throw new IllegalArgumentException("O CPF deve ter 11 caracteres.");
    } else if (convenio != null && convenio.length() > 32) {
      throw new IllegalArgumentException("O convênio deve ter no máximo 32 caracteres.");
    } else if (numeroConvenio != null && numeroConvenio.length() > 32) {
      throw new IllegalArgumentException("O número do convênio deve ter no máximo 32 caracteres.");
    } else if (cep != null && cep.length() != 8) {
      throw new IllegalArgumentException("O CEP deve ter 8 caracteres.");
    } else if (endereco != null && endereco.length() > 128) {
      throw new IllegalArgumentException("O endereço deve ter no máximo 128 caracteres.");
    } else if (anotacoes != null && anotacoes.length() > 256) {
      throw new IllegalArgumentException("As anotações devem ter no máximo 256 caracteres.");
    } else if (observacoes != null && observacoes.length() > 256) {
      throw new IllegalArgumentException("As observações devem ter no máximo 256 caracteres.");
    }

    /* Validação da unicidade dos campos únicos */

    if (repository.findByCodigoIgnoreCase(codigo) != null) {
      throw new IllegalArgumentException(
          "Código gerado já cadastrado.\n\n" +
              "A chance disso acontecer é praticamente a mesma que duas pessoas escolherem aleatoriamente a mesma estrela da Via Láctea.");
    } else if (repository.findByNomeCurtoIgnoreCase(nomeCurto) != null) {
      throw new IllegalArgumentException("Nome curto já cadastrado.");
    } else if (repository.findByCertidaoNascimento(certidaoNascimento) != null) {
      throw new IllegalArgumentException("Certidão de nascimento já cadastrada.");
    } else if (repository.findByCpf(cpf) != null) {
      throw new IllegalArgumentException("CPF já cadastrado.");
    }

    /* Validação do campo 'sexo' */

    if (sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("m")) {
      paciente.setSexo("Masculino");
    } else if (sexo.toLowerCase().equals("feminino") || sexo.toLowerCase().equals("f")) {
      paciente.setSexo("Feminino");
    } else {
      throw new IllegalArgumentException("Sexo inválido. Deve ser 'Masculino' ou 'Feminino'.");
    }

    /* Validação do campo 'dataNascimento' */

    if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("A data de nascimento deve ser anterior ou igual à data atual.");
    }

    return paciente;
  }

  public PacienteModel validateUpdateBusinessLogic(PacienteModel paciente, PacienteModel pacienteUpdate) {
    String nome = pacienteUpdate.getNome();
    String nomeCurto = pacienteUpdate.getNomeCurto();
    String sexo = pacienteUpdate.getSexo();
    LocalDate dataNascimento = pacienteUpdate.getDataNascimento();
    String localNascimento = pacienteUpdate.getLocalNascimento();
    String certidaoNascimento = pacienteUpdate.getCertidaoNascimento();
    String cpf = pacienteUpdate.getCpf();
    String convenio = pacienteUpdate.getConvenio();
    String numeroConvenio = pacienteUpdate.getNumeroConvenio();
    String cep = pacienteUpdate.getCep();
    String endereco = pacienteUpdate.getEndereco();
    String anotacoes = pacienteUpdate.getAnotacoes();
    String observacoes = pacienteUpdate.getObservacoes();

    /* Validação do preenchimento dos campos obrigatórios */

    if (nome == null || nome.isEmpty()) {
      throw new IllegalArgumentException("Nome não foi preenchido.");
    } else if (sexo == null || sexo.isEmpty()) {
      throw new IllegalArgumentException("Sexo não foi preenchido.");
    } else if (dataNascimento == null) {
      throw new IllegalArgumentException("Data de nascimento não foi preenchida.");
    }

    /* Validação dos tamanhos dos campos */

    if (nome.length() > 64) {
      throw new IllegalArgumentException("O nome deve ter no máximo 64 caracteres.");
    } else if (nomeCurto != null && nomeCurto.length() > 32) {
      throw new IllegalArgumentException("O nome curto deve ter no máximo 32 caracteres.");
    } else if (sexo.length() > 16) {
      throw new IllegalArgumentException("O sexo deve ter no máximo 16 caracteres.");
    } else if (localNascimento != null && localNascimento.length() > 64) {
      throw new IllegalArgumentException("O local de nascimento deve ter no máximo 64 caracteres.");
    } else if (certidaoNascimento != null && certidaoNascimento.length() > 32) {
      throw new IllegalArgumentException("O número da certidão de nascimento deve ter no máximo 32 caracteres.");
    } else if (cpf != null && cpf.length() != 11) {
      throw new IllegalArgumentException("O CPF deve ter 11 caracteres.");
    } else if (convenio != null && convenio.length() > 32) {
      throw new IllegalArgumentException("O convênio deve ter no máximo 32 caracteres.");
    } else if (numeroConvenio != null && numeroConvenio.length() > 32) {
      throw new IllegalArgumentException("O número do convênio deve ter no máximo 32 caracteres.");
    } else if (cep != null && cep.length() != 8) {
      throw new IllegalArgumentException("O CEP deve ter 8 caracteres.");
    } else if (endereco != null && endereco.length() > 128) {
      throw new IllegalArgumentException("O endereço deve ter no máximo 128 caracteres.");
    } else if (anotacoes != null && anotacoes.length() > 256) {
      throw new IllegalArgumentException("As anotações devem ter no máximo 256 caracteres.");
    } else if (observacoes != null && observacoes.length() > 256) {
      throw new IllegalArgumentException("As observações devem ter no máximo 256 caracteres.");
    }

    /* Validação da unicidade dos campos únicos */

    if (!paciente.equals(repository.findByNomeCurtoIgnoreCase(nomeCurto))) {
      throw new IllegalArgumentException("Nome curto já cadastrado.");
    } else if (!paciente.equals(repository.findByCertidaoNascimento(certidaoNascimento))) {
      throw new IllegalArgumentException("Certidão de nascimento já cadastrada.");
    } else if (!paciente.equals(repository.findByCpf(cpf))) {
      throw new IllegalArgumentException("CPF já cadastrado.");
    }

    /* Validação do campo 'sexo' */

    if (sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("m")) {
      pacienteUpdate.setSexo("Masculino");
    } else if (sexo.toLowerCase().equals("feminino") || sexo.toLowerCase().equals("f")) {
      pacienteUpdate.setSexo("Feminino");
    } else {
      throw new IllegalArgumentException("Sexo inválido. Deve ser 'Masculino' ou 'Feminino'.");
    }

    /* Validação do campo 'dataNascimento' */

    if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("A data de nascimento deve ser anterior ou igual à data atual.");
    }

    return pacienteUpdate;
  }

}
