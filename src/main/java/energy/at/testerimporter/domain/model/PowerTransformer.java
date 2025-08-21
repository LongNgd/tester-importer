package energy.at.testerimporter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "power_transformer")
public class PowerTransformer extends ConductingEquipment {
    @Column(name = "vector_group")
    private String vectorGroup;
}
