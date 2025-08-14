package energy.at.testerimporter.service.impl;

import energy.at.testerimporter.domain.model.Substation;
import energy.at.testerimporter.repository.SubstationRepository;
import energy.at.testerimporter.service.SubstationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubstationServiceImpl implements SubstationService {
    private final SubstationRepository substationRepository;

    @Override
    public List<Substation> listSubstation() {
        return substationRepository.findAll();
    }
}
