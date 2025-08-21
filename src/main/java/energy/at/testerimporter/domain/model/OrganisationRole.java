package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organisation_role")
@Inheritance(strategy = InheritanceType.JOINED)
public class OrganisationRole extends IdentifiedObject {
    @ManyToOne
    @JoinColumn(name = "organisation")
    private Organisation organisation;
}
