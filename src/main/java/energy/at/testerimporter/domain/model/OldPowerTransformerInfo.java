package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "old_power_transformer_info")
public class OldPowerTransformerInfo extends PowerTransformerInfo {
    private String category;
    private String phases;

    @Column(name = "vector_group")
    private String vectorGroup;

    @ManyToOne
    @JoinColumn(name = "rated_frequency")
    private Frequency ratedFrequency;

    @ManyToOne
    @JoinColumn(name = "impedance_temperature")
    private Temperature impedanceTemperature;
}
