package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.TelephoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelephoneNumberRepository extends JpaRepository<TelephoneNumber, UUID> {
    Optional<TelephoneNumber> findByLocalNumber(String localNumber);
}
