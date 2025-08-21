package energy.at.testerimporter.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy.at.testerimporter.domain.model.*;
import energy.at.testerimporter.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class JsonSubstationLoader {
    private final TownDetailRepository townDetailRepository;
    private final StreetAddressRepository streetAddressRepository;
    private final ElectronicAddressRepository electronicAddressRepository;
    private final LocationRepository locationRepository;
    private final SubstationRepository substationRepository;
    private final OrganisationRepository organisationRepository;

    @Transactional
    public void importFromJson(MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file.getInputStream());

        for (JsonNode node : root) {
            // Validate required input
            Assert.notNull(!node.hasNonNull("Tên đơn vị"), "Missing required field 'Tên đơn vị': " + node);
            Assert.notNull(!node.hasNonNull("Parent_company"), "Missing required field 'Parent_company': " + node);

            Optional<Organisation> parent = organisationRepository.findByName(node.get("Parent_company").asText());
            Assert.isTrue(parent.isPresent(), "Parent company not found in organisation data: " + node.get("Parent_company"));

            // TownDetail
            String district = node.get("Quận/Huyện").asText(null);
            String ward = node.get("Phường/Xã").asText(null);
            String city = node.get("Tỉnh/Thành phố").asText(null);

            TownDetail townDetail;
            if (district != null || ward != null || city != null) { // check if town detail exists
                townDetail = townDetailRepository
                        .findByCityAndDistrictTownAndWardCommune(city, district, ward)
                        .orElseGet(() -> {
                            TownDetail td = new TownDetail();
                            td.setMrid(UUID.randomUUID());
                            td.setCity(city);
                            td.setDistrictTown(district);
                            td.setWardCommune(ward);
                            return townDetailRepository.save(td);
                        });
            } else {
                townDetail = null;
            }

            // StreetAddress
            String street = node.get("Đường").asText();

            StreetAddress streetAddress;
            if (street != null || townDetail != null) { // check if street address exists
                streetAddress = streetAddressRepository
                        .findByStreetDetailAndTownDetail(street, townDetail)
                        .orElseGet(() -> {
                            StreetAddress sa = new StreetAddress();
                            sa.setMrid(UUID.randomUUID());
                            sa.setStreetDetail(street);
                            sa.setTownDetail(townDetail);
                            return streetAddressRepository.save(sa);
                        });
            } else {
                streetAddress = null;
            }

            // ElectronicAddress
            String email = node.get("Email").asText(null);

            ElectronicAddress electronicAddress;
            if (email != null) { // check if electronic address exists
                electronicAddress = electronicAddressRepository
                        .findByEmail(email)
                        .orElseGet(() -> {
                            ElectronicAddress ea = new ElectronicAddress();
                            ea.setMrid(UUID.randomUUID());
                            ea.setEmail(email);
                            return electronicAddressRepository.save(ea);
                        });
            } else {
                electronicAddress = null;
            }


            // Location
            String geoRef = node.get("Kinh độ, vĩ độ").asText(null);

            Location location;
            if (geoRef != null || streetAddress != null || electronicAddress != null) { // check if location exists
                location = locationRepository
                        .findByGeoInfoReferenceAndMainAddressAndElectronicAddress(geoRef, streetAddress, electronicAddress)
                        .orElseGet(() -> {
                            Location l = new Location();
                            l.setMrid(UUID.randomUUID());
                            l.setGeoInfoReference(geoRef);
                            l.setMainAddress(streetAddress);
                            l.setElectronicAddress(electronicAddress);
                            return locationRepository.save(l);
                        });
            } else {
                location = null;
            }

            // Substation
            substationRepository.findByName(node.get("Tên đơn vị").asText()) // check if substation exists
                    .orElseGet(() -> {
                        Substation substation = new Substation();
                        substation.setMrid(UUID.randomUUID());
                        substation.setOwner(parent.get());
                        substation.setName(node.get("Tên đơn vị").asText());
                        if (node.get("Tag") != null) {
                            substation.setAliasName(node.get("Tag").asText());
                        }
                        if (node.get("Ghi chú nội bộ") != null) {
                            substation.setDescription(node.get("Ghi chú nội bộ").asText());
                        }
                        substation.setLocation(location);
                        return substationRepository.save(substation);
                    });
        }

        log.info("JSON import completed.");
    }
}
