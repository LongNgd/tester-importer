package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipment")
@Inheritance(strategy = InheritanceType.JOINED)
public class Equipment extends PowerSystemResource {
    @ManyToOne
    @JoinColumn(name = "equipment_container")
    private EquipmentContainer equipmentContainer;
}
