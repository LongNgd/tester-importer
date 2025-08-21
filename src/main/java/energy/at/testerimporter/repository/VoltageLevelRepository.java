package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.VoltageLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoltageLevelRepository extends JpaRepository<VoltageLevel, UUID> {

}
