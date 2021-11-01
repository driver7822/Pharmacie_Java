package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;

import java.util.Date;
import java.util.List;

public interface InterfPrescriptionService extends InterfService<Prescription>{
    public List<Prescription> getPrescriptionByPatient(Patient patient);
}
