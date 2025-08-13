package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.StreetAddress;
import energy.at.testerimporter.domain.model.TownDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StreetAddressRepository extends JpaRepository<StreetAddress, UUID> {
    Optional<StreetAddress> findByStreetDetailAndTownDetail(String streetDetail, TownDetail townDetail);
}
