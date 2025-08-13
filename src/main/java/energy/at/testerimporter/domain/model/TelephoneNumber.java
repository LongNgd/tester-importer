package energy.at.testerimporter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "telephone_number")
public class TelephoneNumber {
    @Id
    private UUID mrid;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "dial_out")
    private String dialOut;
    private String extension;

    @Column(name = "international_prefix")
    private String internationalPrefix;

    @Column(name = "itu_phone")
    private String ituPhone;

    @Column(name = "local_number")
    private String localNumber;
}
