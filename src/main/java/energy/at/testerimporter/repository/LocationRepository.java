package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.ElectronicAddress;
import energy.at.testerimporter.domain.model.Location;
import energy.at.testerimporter.domain.model.StreetAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    Optional<Location> findByGeoInfoReferenceAndMainAddressAndElectronicAddress(String geoRef, StreetAddress streetAddress, ElectronicAddress electronicAddress);
}
