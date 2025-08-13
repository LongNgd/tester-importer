package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_asset_model")
public class ProductAssetModel extends IdentifiedObject {
    @Column(name = "catalogue_number")
    private String catalogueNumber;

    @Column(name = "corporate_standard_kind")
    private String corporateStandardKind;

    @Column(name = "drawing_number")
    private String drawingNumber;

    @Column(name = "instruction_manual")
    private String instructionManual;

    @Column(name = "model_version")
    private String modelVersion;

    @Column(name = "model_number")
    private String modelNumber;

    @ManyToOne
    @JoinColumn(name = "overall_length")
    private Length overallLength;

    @Column(name = "style_number")
    private String styleNumber;

    @ManyToOne
    @JoinColumn(name = "weight_total")
    private Mass weightTotal;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;
}
