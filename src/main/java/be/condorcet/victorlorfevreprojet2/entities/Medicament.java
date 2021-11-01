package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "medicament", schema = "public", catalog = "d4khrccc1imesr")
public class Medicament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmedicament;
    private String code;
    private String nom;
    private String description;
    private BigDecimal prixunitaire;

}
