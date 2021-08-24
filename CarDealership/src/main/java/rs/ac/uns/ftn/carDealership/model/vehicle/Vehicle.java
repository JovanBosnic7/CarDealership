package rs.ac.uns.ftn.carDealership.model.vehicle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private UUID vehicleId;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "engine_id", referencedColumnName = "engine_id")
    private Engine engine;

    @ManyToMany
    private Set<ImageModel> images = new HashSet<>();

    @ManyToMany
    private Set<Accessories> accessories = new HashSet<>();

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "has_action")
    private Boolean hasAction;

    @Column(name = "price")
    private int price;

    @Column(name = "number_of_doors")
    private int numberOfDoors;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "air_conditioning")
    private String airConditioning;

    @Column(name = "chassis_number")
    private String chassisNumber;

    @Column(name = "interior_material")
    private String interiorMaterial;

    @Column(name = "gear_box_type")
    private String gearBoxType;

    @Column(name = "car_body")
    private String carBody;

    @Column(name = "car_body_color")
    private String carBodyColor;

    @Column(name = "vehicle_status")
    private String vehicle_status;

}

