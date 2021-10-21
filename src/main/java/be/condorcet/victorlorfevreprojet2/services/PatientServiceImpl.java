package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class PatientServiceImpl implements InterfPatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) throws Exception {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient read(Integer id) throws Exception{
        Optional<Patient> opat = patientRepository.findById(id);
        return opat.get();
    }

    @Override
    public Patient update(Patient patient) throws Exception {
        Integer id = patient.getIdPatient();
        Patient oldPat = read(id);
        oldPat.setNom(patient.getNom());
        oldPat.setPrenom(patient.getPrenom());
        oldPat.setNss(patient.getNss());
        oldPat.setDateNaissance(patient.getDateNaissance());
        patientRepository.save(oldPat);
        return read(oldPat.getIdPatient());
    }

    @Override
    public void delete(Patient patient) throws Exception{
        patientRepository.deleteById(patient.getIdPatient());
    }

    @Override
    public List<Patient> read(String nom) {
        return patientRepository.findByNomLike(nom);
    }
}
