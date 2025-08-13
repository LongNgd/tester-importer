package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "mass")
public class Mass {
    @Id
    private UUID mrid;

    private String multiplier;
    private String unit;
    private String value;
}
