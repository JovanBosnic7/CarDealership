package rs.ac.uns.ftn.carDealership.model.carDealership;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import rs.ac.uns.ftn.carDealership.model.users.Client;
import rs.ac.uns.ftn.carDealership.model.vehicle.Vehicle;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "reservation_id", nullable = false, unique = true)
    private UUID reservationId;

    @Column(name = "date_of_validity")
    private Date dateOfValidity;

    @Column(name = "type_of_payment")
    private String typeOfPayment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "reservation_status_id", referencedColumnName = "reservation_status_id")
    private ReservationStatus reservationStatus;
}
