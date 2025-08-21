package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Bay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BayRepository extends JpaRepository<Bay, UUID> {
    Optional<Bay> findByName(String bayName);
}
