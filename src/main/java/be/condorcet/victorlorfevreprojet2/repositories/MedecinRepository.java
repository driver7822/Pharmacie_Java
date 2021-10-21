package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Integer> {
    public List<Medecin> findByNomLike(String nom);
    public List<Medecin> findByNomLikeAndPrenomLike(String nom,String prenom);
    public List<Medecin> findByTel(String tel);

}
