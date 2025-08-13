package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "organisation")
public class Organisation extends IdentifiedObject {
    @Column(name = "tax_code")
    private String taxCode;

    @ManyToOne
    @JoinColumn(name = "parent_organisation")
    private ParentOrganisation parentOrganisation;

    @ManyToOne
    @JoinColumn(name = "street_address")
    private StreetAddress streetAddress;

    @ManyToOne
    @JoinColumn(name = "postal_address")
    private StreetAddress postalAddress;

    @ManyToOne
    @JoinColumn(name = "electronic_address")
    private ElectronicAddress electronicAddress;

    @ManyToOne
    @JoinColumn(name = "phone")
    private TelephoneNumber phone;
}
