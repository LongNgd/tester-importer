package energy.at.testerimporter.controller;

import energy.at.testerimporter.domain.model.Organisation;
import energy.at.testerimporter.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organisation")
@RequiredArgsConstructor
public class OrganisationController {
    private final OrganisationService organisationService;

    @GetMapping
    public ResponseEntity<List<Organisation>> listOrganisation() {
        List<Organisation> organisations = organisationService.listOrganisation();
        return ResponseEntity.ok(organisations);
    }
}
