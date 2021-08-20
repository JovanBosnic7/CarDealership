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
@Table(name= "images")
public class ImageModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "image_id", nullable = false, unique = true)
    private UUID imageId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

}
