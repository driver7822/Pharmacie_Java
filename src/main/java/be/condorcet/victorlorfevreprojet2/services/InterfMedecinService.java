package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;

import java.util.List;

public interface InterfMedecinService extends InterfService<Medecin>{
    public List<Medecin> read(String nom);
    public List<Medecin> readMatricule(String matricule);
    public List<Medecin> readAllMedecin();
}
