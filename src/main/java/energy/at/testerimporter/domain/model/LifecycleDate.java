package energy.at.testerimporter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "lifecycle_date")
public class LifecycleDate {
    @Id
    private UUID mrid;

    @Column(name = "installation_date")
    private String installationDate;

    @Column(name = "manufactured_date")
    private String manufacturedDate;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "received_date")
    private String receivedDate;

    @Column(name = "removal_date")
    private String removalDate;

    @Column(name = "retired_date")
    private String retiredDate;
}
