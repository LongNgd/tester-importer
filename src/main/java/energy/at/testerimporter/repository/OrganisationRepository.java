package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
    Optional<Organisation> findByName(String name);
}
