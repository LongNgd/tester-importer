package energy.at.testerimporter.repository;

import energy.at.testerimporter.domain.model.TownDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TownDetailRepository extends JpaRepository<TownDetail, UUID> {
    Optional<TownDetail> findByCityAndWardCommuneAndCountry(String city, String wardOrCommune, String country);

    Optional<TownDetail> findByCityAndDistrictTownAndWardCommune(String city, String district, String ward);
}
