package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class MedecinServiceImpl implements InterfMedecinService{
    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Medecin create(Medecin medecin) throws Exception{
        medecinRepository.save(medecin);
        return medecin;
    }

    @Override
    public Medecin read(Integer id) throws Exception {
        Optional<Medecin> opmed = medecinRepository.findById(id);
        return opmed.get();
    }

    @Override
    public Medecin update(Medecin medecin) throws Exception {
        Integer id = medecin.getIdmedecin();
        Medecin oldMed = read(id);
        oldMed.setNom(medecin.getNom());
        oldMed.setPrenom(medecin.getPrenom());
        oldMed.setMatricule(medecin.getMatricule());
        oldMed.setTel(medecin.getTel());
        medecinRepository.save(oldMed);
        return read(oldMed.getIdmedecin());
    }

    @Override
    public void delete(Medecin medecin) throws Exception {
        medecinRepository.deleteById(medecin.getIdmedecin());
    }

    @Override
    public List<Medecin> read(String nom){
        return medecinRepository.findByNomLike(nom);
    }

    @Override
    public List<Medecin> readMatricule(String matricule) { return medecinRepository.findByMatricule(matricule);}

    @Override
    public List<Medecin> readAllMedecin() {return medecinRepository.getAllMedecin();}
}
