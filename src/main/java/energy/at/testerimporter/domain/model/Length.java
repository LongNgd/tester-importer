package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "length")
public class Length {
    @Id
    private UUID mrid;

    private String multiplier;
    private String unit;
    private Float value;
}
