package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "manufacturer")
public class Manufacturer extends IdentifiedObject {

}
