package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.OldPowerTransformerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OldPowerTransformerInfoRepository extends JpaRepository<OldPowerTransformerInfo, UUID> {
    Optional<OldPowerTransformerInfo> findByMrid(UUID optiMrid);
}
