package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medicament;
import be.condorcet.victorlorfevreprojet2.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class MedicamentServiceImpl implements InterfMedicamentService{
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Override
    public Medicament create(Medicament medicament) throws Exception{
        medicamentRepository.save(medicament);
        return medicament;
    }

    @Override
    public Medicament read(Integer id) throws Exception{
        Optional<Medicament> omedic = medicamentRepository.findById(id);
        return omedic.get();
    }

    @Override
    public Medicament update(Medicament medicament) throws Exception{
        Integer id = medicament.getIdmedicament();
        Medicament oldMedic = read(id);
        oldMedic.setCode(medicament.getCode());
        oldMedic.setNom(medicament.getNom());
        oldMedic.setDescription(medicament.getDescription());
        oldMedic.setPrixunitaire(medicament.getPrixunitaire());
        medicamentRepository.save(oldMedic);
        return read(oldMedic.getIdmedicament());
    }

    @Override
    public void delete(Medicament medicament) throws Exception{
        medicamentRepository.deleteById(medicament.getIdmedicament());
    }

    @Override
    public List<Medicament> read(String nom) {
        return medicamentRepository.findByNomLike(nom);
    }

    @Override
    public List<Medicament> readCode(String code) {return medicamentRepository.findByCode(code);}

}
