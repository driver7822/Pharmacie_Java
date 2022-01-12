package be.condorcet.victorlorfevreprojet2.repositories;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
    public List<Prescription> findPrescriptionByMedecin(Medecin medecin);
    public List<Prescription> findPrescriptionByPatient(Patient pat);
    public List<Prescription> findPrescriptionByPatientAndAndMedecin(Patient pat, Medecin medecin);

    @Query("select pres from Prescription pres, Patient pat WHERE pres.patient.idpatient=pat.idpatient ORDER BY pres.medecin.nom")
    public List<Prescription> findPrescriptionByPatientOrderByNomMedecin(Patient pat);

}
