package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfosRepository extends JpaRepository<Infos,Integer> {
    public List<Infos> findByQuantite(Integer quantite);
    public List<Infos> findByIdPrescription(Integer idPrescription);
    public List<Infos> findByIdMedicament(Integer idMedicament);
}
