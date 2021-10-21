package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    public List<Patient> findByNomLike(String nom);
    public List<Patient> findByNomLikeAndPrenomLike(String nom,String Prenom);
    public List<Patient> findByDateNaissance(Date dateNaissance);
}
