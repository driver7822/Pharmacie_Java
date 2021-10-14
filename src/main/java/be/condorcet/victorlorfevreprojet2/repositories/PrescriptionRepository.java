package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
    public List<Prescription> findByIdPrescription(Integer idPrescription);
    public List<Prescription> findByDatePrescription(Date datePrescription);
    public List<Prescription> findByIdMedecin(Integer idMedecin);
    public List<Prescription> findByIdPatient(Integer idPatient);
}
