package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "asset_info")
public class AssetInfo extends IdentifiedObject {
    @ManyToOne
    @JoinColumn(name = "product_asset_model")
    private ProductAssetModel productAssetModel;

    @Column(name = "manufacturer_type")
    private String manufacturerType;
}
