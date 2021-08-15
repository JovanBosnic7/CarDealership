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
@Table(name = "test_drive_status")
public class TestDriveStatus {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "test_drive_status_id", nullable = false, unique = true)
    private UUID testDriveStatusId;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "test_drive_id", referencedColumnName = "test_drive_id")
    private TestDrive testDrive;
}
