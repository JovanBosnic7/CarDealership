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
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "feedback_id", nullable = false, unique = true)
    private UUID feedbackId;

    @Column(name = "description")
    private String description;

    @Column(name = "dateOfPosting")
    private Date dateOfPosting;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;
}
