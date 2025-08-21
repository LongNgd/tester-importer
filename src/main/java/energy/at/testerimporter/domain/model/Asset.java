package energy.at.testerimporter.domain.model;

import energy.at.testerimporter.common.enums.AssetKind;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "asset")
@Inheritance(strategy = InheritanceType.JOINED)
public class Asset extends IdentifiedObject {
    private String type;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "lifecycle_date")
    private LifecycleDate lifecycleDate;

    @ManyToOne
    @JoinColumn(name = "product_asset_model")
    private ProductAssetModel productAssetModel;

    @OneToOne
    @JoinColumn(name = "asset_info")
    private AssetInfo assetInfo;

    @ManyToOne
    @JoinColumn(name = "asset_container")
    private Equipment assetContainer;

    private AssetKind kind;
}
