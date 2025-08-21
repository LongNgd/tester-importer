package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bay")
public class Bay extends EquipmentContainer {
    @ManyToOne
    @JoinColumn(name = "voltage_level")
    private VoltageLevel voltageLevel;
}
