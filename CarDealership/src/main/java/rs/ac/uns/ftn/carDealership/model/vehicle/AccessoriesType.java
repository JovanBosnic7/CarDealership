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
@Table(name = "accessories_types")
public class AccessoriesType {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "accessories_type_id", nullable = false, unique = true)
    private UUID accessoriesTypeId;

    @Column(name="type")
    private rs.ac.uns.ftn.carDealership.model.enums.AccessoriesType type;
}
