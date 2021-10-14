package be.condorcet.victorlorfevreprojet2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "patient", schema = "public", catalog = "d4khrccc1imesr")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;
    private String nss;
    private String nom;
    private String prenom;
    private Date dateNaissance;
}
