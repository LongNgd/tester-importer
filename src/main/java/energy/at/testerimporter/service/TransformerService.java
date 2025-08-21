package energy.at.testerimporter.service;

import energy.at.testerimporter.domain.model.dto.TransformerDTO;

import java.util.List;

public interface TransformerService {
    List<TransformerDTO> listTransformerInfo();
}
