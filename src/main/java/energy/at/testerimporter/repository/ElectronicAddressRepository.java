package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.ElectronicAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ElectronicAddressRepository extends JpaRepository<ElectronicAddress, UUID> {
    Optional<ElectronicAddress> findByEmailAndWeb(String email, String web);

    Optional<ElectronicAddress> findByEmail(String email);
}
