package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.PowerTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PowerTransformerRepository extends JpaRepository<PowerTransformer, UUID> {
    Optional<PowerTransformer> findByName(String powerTransformerName);
}
