package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "power_transformer_info")
@Inheritance(strategy = InheritanceType.JOINED)
public class PowerTransformerInfo extends AssetInfo {

}
