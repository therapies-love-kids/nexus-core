package tlk.nexus_core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tlk.nexus_core.models.VinculoModel;

@Repository
public interface VinculoRepository extends JpaRepository<VinculoModel, Long> {

}
