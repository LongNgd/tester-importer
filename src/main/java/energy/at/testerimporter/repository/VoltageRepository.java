package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Voltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoltageRepository extends JpaRepository<Voltage, UUID> {
    Optional<Voltage> findByValueAndMultiplierAndUnit(Double value, String multiplier, String unit);
}
