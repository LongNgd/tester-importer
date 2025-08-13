package energy.at.testerimporter.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "electronic_address")
public class ElectronicAddress {
    @Id
    private UUID mrid;

    private String email;
    private String lan;
    private String mac;
    private String password;
    private String radio;
    private String user_id;
    private String web;
    private String fax;
}
