package energy.at.testerimporter.controller;

import energy.at.testerimporter.domain.model.Substation;
import energy.at.testerimporter.service.SubstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/substation")
@RequiredArgsConstructor
public class SubstationController {
    private final SubstationService substationService;

    @GetMapping
    public ResponseEntity<List<Substation>> listSubstation() {
        List<Substation> substations = substationService.listSubstation();
        return ResponseEntity.ok(substations);
    }
}
