package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "connectivity_node_container")
@Inheritance(strategy = InheritanceType.JOINED)
public class ConnectivityNodeContainer extends PowerSystemResource{

}
