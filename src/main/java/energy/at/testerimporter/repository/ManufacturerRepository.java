package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
    Optional<Manufacturer> findByName(String name);
}
