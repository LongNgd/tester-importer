package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.BaseVoltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseVoltageRepository extends JpaRepository<BaseVoltage, UUID> {
}
