package tlk.nexus_core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.models.embeddables.ContatosEmbeddable;
import tlk.nexus_core.models.embeddables.EnderecoEmbeddable;
import tlk.nexus_core.repositories.RepresentanteRepository;

@Service
public class RepresentanteService {

  @Autowired
  private RepresentanteRepository repository;

  public RepresentanteModel create(@Valid RepresentanteModel representante) {
    representante = validateCreateBusinessLogic(representante);
    return repository.save(representante);
  }

  public List<RepresentanteModel> getAll() {
    return repository.findAll();
  }

  public RepresentanteModel getById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public RepresentanteModel getByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

  public RepresentanteModel update(Long id, @Valid RepresentanteModel representanteUpdate) {
    RepresentanteModel representante = repository.findById(id).orElse(null);
    if (representante == null) {
      throw new IllegalArgumentException("Representante não encontrado.");
    }
    representanteUpdate = validateUpdateBusinessLogic(representante, representanteUpdate);
    updateDataDB(representante, representanteUpdate);
    return repository.save(representante);
  }

  public RepresentanteModel activate(Long id) {
    RepresentanteModel representante = repository.findById(id).orElse(null);
    if (representante == null) {
      throw new IllegalArgumentException("Representante não encontrado.");
    }
    representante.setAtivo(true);
    return repository.save(representante);
  }

  public RepresentanteModel inactivate(Long id) {
    RepresentanteModel representante = repository.findById(id).orElse(null);
    if (representante == null) {
      throw new IllegalArgumentException("Representante não encontrado.");
    }
    representante.setAtivo(false);
    return repository.save(representante);
  }

  private void updateDataDB(RepresentanteModel representante, RepresentanteModel representanteUpdate) {
    if (representanteUpdate.getAtivo() != null) {
      representante.setAtivo(representanteUpdate.getAtivo());
    }
    if (representanteUpdate.getNome() != null) {
      representante.setNome(representanteUpdate.getNome());
    }
    if (representanteUpdate.getEstadoCivil() != null) {
      representante.setEstadoCivil(representanteUpdate.getEstadoCivil());
    }
    if (representanteUpdate.getCpf() != null) {
      representante.setCpf(representanteUpdate.getCpf());
    }
    if (representanteUpdate.getRg() != null) {
      representante.setRg(representanteUpdate.getRg());
    }
  }

  // Validação de regras de negócio
  public RepresentanteModel validateCreateBusinessLogic(RepresentanteModel representante) {
    String nome = representante.getNome();
    String estadoCivil = representante.getEstadoCivil();
    String cpf = representante.getCpf();
    String rg = representante.getRg();
    EnderecoEmbeddable endereco = representante.getEndereco();
    ContatosEmbeddable contatos = representante.getContatos();

    // Validação dos campos obrigatórios
    if (nome == null || estadoCivil == null || nome.isEmpty() || estadoCivil.isEmpty() || cpf == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    // Validação dos campos únicos
    if (repository.findByCpf(cpf) != null) {
      throw new IllegalArgumentException("CPF já cadastrado.");
    }

    // Validação dos tamanhos dos campos
    if (nome.length() > 64) {
      throw new IllegalArgumentException("O nome do representante deve ter no máximo 64 caracteres.");
    } else if (estadoCivil.length() > 16) {
      throw new IllegalArgumentException("O estado civil do representante deve ter no máximo 16 caracteres.");
    } else if (cpf.length() != 11) {
      throw new IllegalArgumentException("O CPF do representante deve ter 11 caracteres.");
    } else if (rg != null && rg.length() > 32) {
      throw new IllegalArgumentException("O RG do representante deve ter no máximo 32 caracteres.");
    } else if (endereco.getCep() != null && endereco.getCep().length() != 8) {
      throw new IllegalArgumentException("O CEP deve ter 8 caracteres");
    } else if (endereco.getLogradouro() != null && endereco.getLogradouro().length() > 64) {
      throw new IllegalArgumentException("O logradouro deve ter no máximo 64 caracteres");
    } else if (endereco.getComplemento() != null && endereco.getComplemento().length() > 32) {
      throw new IllegalArgumentException("O complemento deve ter no máximo 32 caracteres");
    } else if (endereco.getBairro() != null && endereco.getBairro().length() > 32) {
      throw new IllegalArgumentException("O bairro deve ter no máximo 32 caracteres");
    } else if (endereco.getCidade() != null && endereco.getCidade().length() > 32) {
      throw new IllegalArgumentException("A cidade deve ter no máximo 32 caracteres");
    } else if (endereco.getUf() != null && endereco.getUf().length() != 2) {
      throw new IllegalArgumentException("A UF deve ter 2 caracteres");
    } else if (endereco.getNumero() != null && endereco.getNumero().length() > 16) {
      throw new IllegalArgumentException("O número deve ter no máximo 16 caracteres");
    } else if (contatos.getTelefone() != null && contatos.getTelefone().length() > 16) {
      throw new IllegalArgumentException("O telefone deve ter no máximo 16 caracteres");
    } else if (contatos.getCelular() != null && contatos.getCelular().length() > 16) {
      throw new IllegalArgumentException("O celular deve ter no máximo 16 caracteres");
    } else if (contatos.getEmail() != null && contatos.getEmail().length() > 64) {
      throw new IllegalArgumentException("O email deve ter no máximo 64 caracteres");
    }

    // Validação do estado civil
    if (estadoCivil.toLowerCase().equals("solteiro") || estadoCivil.toLowerCase().equals("s")) {
      representante.setEstadoCivil("Solteiro");
    } else if (estadoCivil.toLowerCase().equals("casado") || estadoCivil.toLowerCase().equals("c")) {
      representante.setEstadoCivil("Casado");
    } else if (estadoCivil.toLowerCase().equals("divorciado") || estadoCivil.toLowerCase().equals("d")) {
      representante.setEstadoCivil("Divorciado");
    } else if (estadoCivil.toLowerCase().equals("viuvo") || estadoCivil.toLowerCase().equals("v")) {
      representante.setEstadoCivil("Viuvo");
    } else {
      throw new IllegalArgumentException("Estado civil inválido. Deve ser 'Solteiro', 'Casado', 'Divorciado' ou 'Viuvo'.");
    }

    return representante;
  }

  public RepresentanteModel validateUpdateBusinessLogic(RepresentanteModel representante, RepresentanteModel representanteUpdate) {
    String nome = representanteUpdate.getNome();
    String estadoCivil = representanteUpdate.getEstadoCivil();
    String cpf = representanteUpdate.getCpf();
    String rg = representanteUpdate.getRg();
    EnderecoEmbeddable endereco = representanteUpdate.getEndereco();
    ContatosEmbeddable contatos = representanteUpdate.getContatos();

    // Validação dos campos obrigatórios
    if (nome == null || estadoCivil == null || nome.isEmpty() || estadoCivil.isEmpty() || cpf == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    // Validação dos campos únicos
    if (!representante.equals(repository.findByCpf(cpf))) {
      throw new IllegalArgumentException("CPF já cadastrado.");
    }

    // Validação dos tamanhos dos campos
    if (nome.length() > 64) {
      throw new IllegalArgumentException("O nome do representante deve ter no máximo 64 caracteres.");
    } else if (estadoCivil.length() > 16) {
      throw new IllegalArgumentException("O estado civil do representante deve ter no máximo 16 caracteres.");
    } else if (cpf.length() != 11) {
      throw new IllegalArgumentException("O CPF do representante deve ter 11 caracteres.");
    } else if (rg != null && rg.length() > 32) {
      throw new IllegalArgumentException("O RG do representante deve ter no máximo 32 caracteres.");
    } else if (endereco.getCep() != null && endereco.getCep().length() != 8) {
      throw new IllegalArgumentException("O CEP deve ter 8 caracteres");
    } else if (endereco.getLogradouro() != null && endereco.getLogradouro().length() > 64) {
      throw new IllegalArgumentException("O logradouro deve ter no máximo 64 caracteres");
    } else if (endereco.getComplemento() != null && endereco.getComplemento().length() > 32) {
      throw new IllegalArgumentException("O complemento deve ter no máximo 32 caracteres");
    } else if (endereco.getBairro() != null && endereco.getBairro().length() > 32) {
      throw new IllegalArgumentException("O bairro deve ter no máximo 32 caracteres");
    } else if (endereco.getCidade() != null && endereco.getCidade().length() > 32) {
      throw new IllegalArgumentException("A cidade deve ter no máximo 32 caracteres");
    } else if (endereco.getUf() != null && endereco.getUf().length() != 2) {
      throw new IllegalArgumentException("A UF deve ter 2 caracteres");
    } else if (endereco.getNumero() != null && endereco.getNumero().length() > 16) {
      throw new IllegalArgumentException("O número deve ter no máximo 16 caracteres");
    } else if (contatos.getTelefone() != null && contatos.getTelefone().length() > 16) {
      throw new IllegalArgumentException("O telefone deve ter no máximo 16 caracteres");
    } else if (contatos.getCelular() != null && contatos.getCelular().length() > 16) {
      throw new IllegalArgumentException("O celular deve ter no máximo 16 caracteres");
    } else if (contatos.getEmail() != null && contatos.getEmail().length() > 64) {
      throw new IllegalArgumentException("O email deve ter no máximo 64 caracteres");
    }

    // Validação do estado civil
    if (estadoCivil.toLowerCase().equals("solteiro") || estadoCivil.toLowerCase().equals("s")) {
      representanteUpdate.setEstadoCivil("Solteiro");
    } else if (estadoCivil.toLowerCase().equals("casado") || estadoCivil.toLowerCase().equals("c")) {
      representanteUpdate.setEstadoCivil("Casado");
    } else if (estadoCivil.toLowerCase().equals("divorciado") || estadoCivil.toLowerCase().equals("d")) {
      representanteUpdate.setEstadoCivil("Divorciado");
    } else if (estadoCivil.toLowerCase().equals("viuvo") || estadoCivil.toLowerCase().equals("v")) {
      representanteUpdate.setEstadoCivil("Viuvo");
    } else {
      throw new IllegalArgumentException("Estado civil inválido. Deve ser 'Solteiro', 'Casado', 'Divorciado' ou 'Viuvo'.");
    }

    return representanteUpdate;
  }

}
