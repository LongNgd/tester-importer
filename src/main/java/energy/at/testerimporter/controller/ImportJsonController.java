package energy.at.testerimporter.controller;

import energy.at.testerimporter.helper.JsonTransformerLoader;
import energy.at.testerimporter.helper.JsonOrganisationLoader;
import energy.at.testerimporter.helper.JsonSubstationLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class ImportJsonController {
    private final JsonOrganisationLoader jsonOrganisationLoader;
    private final JsonSubstationLoader jsonSubstationLoader;
    private final JsonTransformerLoader jsonTransformerLoader;

    @PostMapping("/organisation")
    public ResponseEntity<?> importOrganisationJson(@RequestParam("file") MultipartFile file) {
        try {
            jsonOrganisationLoader.importFromJson(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/substation")
    public ResponseEntity<?> importSubstationJson(@RequestParam("file") MultipartFile file) {
        try {
            jsonSubstationLoader.importFromJson(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transformer")
    public ResponseEntity<?> importTransformerJson(@RequestParam("file") MultipartFile file) {
        try {
            jsonTransformerLoader.importFromJson(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
