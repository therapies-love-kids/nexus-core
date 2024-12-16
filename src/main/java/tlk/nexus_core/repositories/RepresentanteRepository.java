package tlk.nexus_core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tlk.nexus_core.models.RepresentanteModel;

public interface RepresentanteRepository extends JpaRepository<RepresentanteModel, Long> {

  List<RepresentanteModel> findByNomeContaining(String nome);

  List<RepresentanteModel> findByCpf(String cpf);

  List<RepresentanteModel> findByRg(String rg);

}
