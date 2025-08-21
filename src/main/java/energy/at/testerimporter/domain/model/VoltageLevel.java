package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "voltage_level")
public class VoltageLevel extends EquipmentContainer{
    @ManyToOne
    @JoinColumn(name = "base_voltage")
    private BaseVoltage baseVoltage;
}
