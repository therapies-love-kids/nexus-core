package tlk.nexus_core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tlk.nexus_core.models.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Integer> {

  List<PacienteModel> findByNomeContaining(String nome);

  List<PacienteModel> findByNomeCurto(String nomeCurto);

  List<PacienteModel> findByCertidaoNascimento(String certidaoNascimento);

  List<PacienteModel> findByCpf(String cpf);

}
