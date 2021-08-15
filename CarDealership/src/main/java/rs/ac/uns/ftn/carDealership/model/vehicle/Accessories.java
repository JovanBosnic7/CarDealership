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
@Table(name = "accessories")
public class Accessories {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "accessories_id", nullable = false, unique = true)
    private UUID accessoriesId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="accessories_type_id")
    private AccessoriesType type;
}
