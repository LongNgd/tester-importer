package energy.at.testerimporter.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransformerDTO {
    private String powerTransformerName;
    private String phases;
    private String vectorGroup;
    private String ratedFrequency;
    private String frequencyUnit;
    private String type;
    private String serialNumber;
    private String manufacturer;
    private String manufacturingYear;
    private String countryOfOrigin;
    private String voltageLevel;
    private Double baseVoltage;
    private String multiplier;
    private String unit;
    private String bay;
}
