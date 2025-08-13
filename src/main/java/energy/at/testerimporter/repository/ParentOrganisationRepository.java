package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.ParentOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParentOrganisationRepository extends JpaRepository<ParentOrganisation, UUID> {
    Optional<ParentOrganisation> findByMrid(UUID mrid);
}