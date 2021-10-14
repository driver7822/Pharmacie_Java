package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "medicament", schema = "public", catalog = "d4khrccc1imesr")
public class Medicament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicament;
    private String code;
    private String nom;
    private String description;
    private BigDecimal prixUnitaire;
}
