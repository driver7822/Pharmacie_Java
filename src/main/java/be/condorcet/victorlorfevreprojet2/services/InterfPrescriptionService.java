package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;

import java.util.Date;
import java.util.List;

public interface InterfPrescriptionService extends InterfService<Prescription>{
    public List<Prescription> getPrescriptionByPatient(Patient patient);
    public List<Prescription> getPrescriptionByMedecin(Medecin medecin);
    public List<Prescription> getPrescriptionByPatientAndMedecin(Patient patient, Medecin medecin) throws Exception;
    public List<Prescription> getPrescriptionByPatientOrderByNomMedecin(Patient pat) throws Exception;
}
