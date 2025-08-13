package energy.at.testerimporter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "identified_object")
@Inheritance(strategy = InheritanceType.JOINED)
public class IdentifiedObject {
    @Id
    private UUID mrid;
    private String name;
    @Column(name = "alias_name")
    private String aliasName;
    private String description;
}

