package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "infos", schema = "public", catalog = "d4khrccc1imesr")
public class Infos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfo;
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name="idmedicament")
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name = "idprescription")
    private Prescription prescription;
}
