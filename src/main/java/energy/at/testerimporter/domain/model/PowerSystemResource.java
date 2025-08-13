package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "power_system_resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class PowerSystemResource extends IdentifiedObject{
    @ManyToOne
    @JoinColumn(name = "psr_type_id")
    private PsrType prsType;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "asset_datasheet")
    private AssetInfo assetDatasheet;
}
