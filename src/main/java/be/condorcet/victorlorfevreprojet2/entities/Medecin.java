package be.condorcet.victorlorfevreprojet2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "medecin", schema = "public", catalog = "d4khrccc1imesr")
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmedecin;
    private String matricule;
    private String nom;
    private String prenom;
    private String tel;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Prescription> prescriptions;
}
