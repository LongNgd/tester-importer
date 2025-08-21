package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.LifecycleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LifecycleDateRepository extends JpaRepository<LifecycleDate, UUID> {
}
