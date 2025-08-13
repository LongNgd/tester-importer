package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "street_address")
public class StreetAddress {
    @Id
    private UUID mrid;

    private String language;
    private String poBox;
    private String postalCode;
    private String status;
    private String streetDetail;

    @ManyToOne
    @JoinColumn(name = "town_detail")
    private TownDetail townDetail;
}
