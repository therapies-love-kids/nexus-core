package tlk.nexus_core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tlk.nexus_core.models.RepresentanteModel;
import tlk.nexus_core.repositories.RepresentanteRepository;

@Service
public class RepresentanteService {

  @Autowired
  private RepresentanteRepository repository;

  public RepresentanteModel create(@Valid RepresentanteModel representante) {
    representante = validateBusinessLogic(representante);
    return repository.save(representante);
  }

  public List<RepresentanteModel> getAll() {
    return repository.findAll();
  }

  // Validação de regras de negócio
  public RepresentanteModel validateBusinessLogic(RepresentanteModel representante) {
    String nome = representante.getNome();
    String estadoCivil = representante.getEstadoCivil();
    String cpf = representante.getCpf();
    String rg = representante.getRg();
    String contatos = representante.getContatos();

    // Validação dos campos obrigatórios
    if (nome == null || estadoCivil == null || nome.isEmpty() || estadoCivil.isEmpty() || cpf == null) {
      throw new IllegalArgumentException("Campos obrigatórios não foram preenchidos.");
    }

    // Validação dos campos únicos
    if (repository.findByCpf(cpf) != null && repository.findByCpf(cpf).size() > 0) {
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
    } else if (contatos != null && contatos.length() > 128) {
      throw new IllegalArgumentException("Os contatos do representante devem ter no máximo 128 caracteres.");
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

}
