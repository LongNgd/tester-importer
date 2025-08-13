package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "substation")
public class Substation extends EquipmentContainer{
    private String generation;
    private String industry;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Organisation owner;
}
