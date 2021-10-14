package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "infos", schema = "public", catalog = "d4khrccc1imesr")
public class infos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfo;
    private Integer quantite;
    private Integer idPrescription;
    private Integer idMedicament;
}
