package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "location")
public class Location extends IdentifiedObject {
    private String direction;

    @ManyToOne
    @JoinColumn(name = "electronic_address")
    private ElectronicAddress electronicAddress;

    @Column(name = "geo_info_reference")
    private String geoInfoReference;

    @ManyToOne
    @JoinColumn(name = "main_address")
    private StreetAddress mainAddress;

    @ManyToOne
    @JoinColumn(name = "phone")
    private TelephoneNumber phone;

    @ManyToOne
    @JoinColumn(name = "secondary_address")
    private StreetAddress secondaryAddress;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;

    private String type;
}
