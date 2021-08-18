package rs.ac.uns.ftn.carDealership.model.vehicle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "model_id", nullable = false, unique = true)
    private UUID modelId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "mark_id")
    private Mark mark;
}
