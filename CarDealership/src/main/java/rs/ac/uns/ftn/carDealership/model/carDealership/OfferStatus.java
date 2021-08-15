package rs.ac.uns.ftn.carDealership.model.carDealership;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offer_status")
public class OfferStatus {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "offer_status_id", nullable = false, unique = true)
    private UUID offerStatusId;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "offer_id")
    private Offer offer;
}
