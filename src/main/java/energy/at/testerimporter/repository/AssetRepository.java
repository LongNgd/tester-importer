package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findBySerialNumber(String assetSerialNumber);
}
