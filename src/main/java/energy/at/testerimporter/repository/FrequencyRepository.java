package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FrequencyRepository extends JpaRepository<Frequency, UUID> {
    Optional<Frequency> findByValueAndUnit(String frequencyValue, String frequencyUnit);
}
