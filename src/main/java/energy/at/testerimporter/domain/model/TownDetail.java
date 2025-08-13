package energy.at.testerimporter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "town_detail")
public class TownDetail {
    @Id
    private UUID mrid;

    private String code;
    private String country;
    private String section;

    @Column(name = "state_or_province")
    private String stateProvince;

    @Column(name = "ward_or_commune")
    private String wardCommune;
    private String city;

    @Column(name = "district_or_town")
    private String districtTown;
}
