package tlk.nexus_core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tlk.nexus_core.models.RepresentanteModel;

public interface RepresentanteRepository extends JpaRepository<RepresentanteModel, Long> {

  List<RepresentanteModel> findByNomeContainingIgnoreCase(String nome);

  RepresentanteModel findByCpf(String cpf);

}
