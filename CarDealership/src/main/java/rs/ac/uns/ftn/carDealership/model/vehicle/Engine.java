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
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "engine_id", nullable = false, unique = true)
    private UUID engineId;

    @Column(name = "engine_number")
    private String engineNumber;

    @ManyToOne
    @JoinColumn(name = "engine_specification_id", referencedColumnName = "engine_specification_id")
    private EngineSpecification engineSpecification;
}
