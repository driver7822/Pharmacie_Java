package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
    public List<Prescription> findByDatePrescription(Date datePrescription);
    public List<Prescription> findPrescriptionByMedecin(Integer idMedecin);
    public List<Prescription> findPrescriptionsByPatient(Patient pat);
}
