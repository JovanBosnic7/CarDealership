package rs.ac.uns.ftn.carDealership.model.carDealership;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "work_times")
public class WorkTime {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "work_time_id", nullable = false, unique = true)
    private UUID workTimeId;

    @Column(name = "day")
    private Date day;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

}
