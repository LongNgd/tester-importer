package energy.at.testerimporter.controller;

import energy.at.testerimporter.domain.model.dto.TransformerDTO;
import energy.at.testerimporter.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transformer")
@RequiredArgsConstructor
public class TransformerController {
    private final TransformerService transformerService;

    @GetMapping("info")
    public ResponseEntity<List<TransformerDTO>> listTransformerInfo() {
        List<TransformerDTO> transformerDTOs = transformerService.listTransformerInfo();
        return ResponseEntity.ok(transformerDTOs);
    }
}
