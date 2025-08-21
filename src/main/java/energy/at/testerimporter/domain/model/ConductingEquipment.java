package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "conducting_equipment")
@Inheritance(strategy = InheritanceType.JOINED)
public class ConductingEquipment extends Equipment {
}
