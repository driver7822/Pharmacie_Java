package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Integer> {
    public List<Medicament> findByPrixunitaire(BigDecimal prixunitaire);
    public List<Medicament> findByNomLike(String nom);
    public List<Medicament> findByDescriptionLike(String description);
    public List<Medicament> findByCode(String code);
}
