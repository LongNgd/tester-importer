package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "base_voltage")
public class BaseVoltage extends IdentifiedObject {
    @ManyToOne
    @JoinColumn(name = "nominal_voltage")
    private Voltage nominalVoltage;
}
