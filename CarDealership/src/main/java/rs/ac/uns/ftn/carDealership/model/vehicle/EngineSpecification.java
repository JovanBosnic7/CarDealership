package rs.ac.uns.ftn.carDealership.model.vehicle;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import rs.ac.uns.ftn.carDealership.model.enums.DriveType;
import rs.ac.uns.ftn.carDealership.model.enums.EmissionClass;
import rs.ac.uns.ftn.carDealership.model.enums.FuelType;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "engine_specifications")
public class EngineSpecification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "engine_specification_id", nullable = false, unique = true)
    private UUID engineSpecificationId;

    @Column(name = "emission_class")
    private EmissionClass emissionClass;

    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "drive_tpe")
    private DriveType driveType;

    @Column(name = "engine_power")
    private int enginePower;

    @Column(name = "engine_displacment")
    private int engineDisplacment;

    @Column(name = "engine_model")
    private String engineModel;
}
