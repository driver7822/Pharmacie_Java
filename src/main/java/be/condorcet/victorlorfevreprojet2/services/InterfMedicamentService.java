package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medicament;

import java.util.List;

public interface InterfMedicamentService extends InterfService<Medicament>{
    public List<Medicament> read(String nom);
    public List<Medicament> readCode(String code);
}
