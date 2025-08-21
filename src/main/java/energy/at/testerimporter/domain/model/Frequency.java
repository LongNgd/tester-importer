package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "frequency")
public class Frequency {
    @Id
    private UUID mrid;

    private String value;
    private String multiplier;
    private String unit;
}
