package energy.at.testerimporter.service.impl;

import energy.at.testerimporter.domain.model.Organisation;
import energy.at.testerimporter.repository.OrganisationRepository;
import energy.at.testerimporter.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {
    private final OrganisationRepository organisationRepository;

    @Override
    public List<Organisation> listOrganisation() {
        return organisationRepository.findAll();
    }
}
