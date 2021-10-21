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
        Integer id = medicament.getIdMedicament();
        Medicament oldMedic = read(id);
        oldMedic.setCode(medicament.getCode());
        oldMedic.setNom(medicament.getNom());
        oldMedic.setDescription(medicament.getDescription());
        oldMedic.setPrixUnitaire(medicament.getPrixUnitaire());
        medicamentRepository.save(oldMedic);
        return read(oldMedic.getIdMedicament());
    }

    @Override
    public void delete(Medicament medicament) throws Exception{
        medicamentRepository.deleteById(medicament.getIdMedicament());
    }

    @Override
    public List<Medicament> read(String nom) {
        return medicamentRepository.findByNomLike(nom);
    }

}
