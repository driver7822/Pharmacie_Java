package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "prescription", schema = "public", catalog = "d4khrccc1imesr")
public class Prescription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrescription;
    private Date datePrescription;

    @ManyToOne
    @JoinColumn(name="idpatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "idmedecin")
    private Medecin  medecin;

}
