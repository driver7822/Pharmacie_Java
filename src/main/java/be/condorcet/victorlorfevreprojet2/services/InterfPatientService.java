package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Patient;

import java.util.List;

public interface InterfPatientService extends InterfService<Patient>{
    public List<Patient> read(String nom);
    public List<Patient> readNss(String nss);
}
