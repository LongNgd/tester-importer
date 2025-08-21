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

import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class JsonOrganisationLoader {
    private final TownDetailRepository townDetailRepository;
    private final StreetAddressRepository streetAddressRepository;
    private final ElectronicAddressRepository electronicAddressRepository;
    private final TelephoneNumberRepository telephoneNumberRepository;
    private final ParentOrganisationRepository parentOrganisationRepository;
    private final OrganisationRepository organisationRepository;

    @Transactional
    public void importFromJson(MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file.getInputStream());

        // Add organisation and related information
        for (JsonNode node : root) {
            // Validate required input
            Assert.notNull(!node.hasNonNull("Tên"), "Missing organisation's name, skipping record: " + node);

            // TownDetail
            String city = node.get("Tỉnh/Thành phố").asText(null);
            String ward = node.get("Phường").asText(null);
            String country = node.get("Country").asText(null);

            TownDetail townDetail;
            if (city != null || ward != null || country != null) { // check if town detail exists
                townDetail = townDetailRepository
                        .findByCityAndWardCommuneAndCountry(city, ward, country)
                        .orElseGet(() -> {
                            TownDetail td = new TownDetail();
                            td.setMrid(UUID.randomUUID());
                            td.setCity(city);
                            td.setWardCommune(ward);
                            td.setCountry(country);
                            return townDetailRepository.save(td);
                        });
            } else {
                townDetail = null;
            }

            // StreetAddress
            String street = node.get("Địa chỉ").asText(null);

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
            String web = node.get("Website Link").asText(null);

            ElectronicAddress electronicAddress;
            if (email != null || web != null) { // check if electronic address exists
                electronicAddress = electronicAddressRepository
                        .findByEmailAndWeb(email, web)
                        .orElseGet(() -> {
                            ElectronicAddress ea = new ElectronicAddress();
                            ea.setMrid(UUID.randomUUID());
                            ea.setEmail(email);
                            ea.setWeb(web);
                            return electronicAddressRepository.save(ea);
                        });
            } else {
                electronicAddress = null;
            }

            // TelephoneNumber
            String phone = node.get("Điện thoại").asText(null);

            TelephoneNumber telephoneNumber;
            if (phone != null) { // check if telephone number exists
                telephoneNumber = telephoneNumberRepository
                        .findByLocalNumber(phone)
                        .orElseGet(() -> {
                            TelephoneNumber tn = new TelephoneNumber();
                            tn.setMrid(UUID.randomUUID());
                            tn.setLocalNumber(phone);
                            return telephoneNumberRepository.save(tn);
                        });
            } else {
                telephoneNumber = null;
            }

            // Organisation
            organisationRepository.findByName(node.get("Tên").asText()) // check if organisation exists
                    .orElseGet(() -> {
                        Organisation org = new Organisation();
                        org.setMrid(UUID.randomUUID());
                        org.setName(node.get("Tên").asText(null));
                        org.setTaxCode(node.get("Mã số thuế").asText(null));
                        org.setDescription(node.get("Ghi chú").asText(null));
                        org.setElectronicAddress(electronicAddress);
                        org.setPhone(telephoneNumber);
                        org.setStreetAddress(streetAddress);
                        return organisationRepository.save(org);
                    });
        }

        // Add parent organisation
        for (JsonNode node : root) {
            String parentName = node.get("ParentCompany").asText(null);
            if (parentName == null) continue;

            // find parent organisation
            Organisation parentOrg = organisationRepository.findByName(parentName)
                    .orElseThrow(() -> new NoSuchElementException("Parent organisation not found with name " + parentName));

            // Check if parent organisation exist in parent_organisation table, if not -> add to table
            ParentOrganisation po = parentOrganisationRepository.findByMrid(parentOrg.getMrid())
                    .orElseGet(() -> {
                        ParentOrganisation parent = new ParentOrganisation();
                        parent.setMrid(parentOrg.getMrid());
                        return parentOrganisationRepository.save(parent);
                    });

            // get organisation and add parent organisation to it
            String orgName = node.get("Tên").asText();
            Organisation org = organisationRepository.findByName(orgName)
                    .orElseThrow(() -> new NoSuchElementException("Organisation not found with name " + orgName));
            org.setParentOrganisation(po);

            organisationRepository.save(org);
        }

        log.info("JSON import completed.");
    }
}
