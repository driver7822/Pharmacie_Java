package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import be.condorcet.victorlorfevreprojet2.repositories.PatientRepository;
import be.condorcet.victorlorfevreprojet2.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class PrescriptionServiceImpl implements InterfPrescriptionService{
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription create(Prescription prescription) throws Exception {
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription read(Integer id) throws Exception{
        return prescriptionRepository.findById(id).get();
    }

    @Override
    public Prescription update(Prescription prescription) throws Exception{
        Integer id = prescription.getIdprescription();
        Prescription oldPres = read(id);
        oldPres.setDateprescription(prescription.getDateprescription());
        prescriptionRepository.save(oldPres);
        return read(oldPres.getIdprescription());
    }

    @Override
    public void delete(Prescription prescription) throws Exception{
        prescriptionRepository.deleteById(prescription.getIdprescription());
    }

    @Override
    public List<Prescription> getPrescriptionByPatient(Patient pat) {
        List<Prescription> lpres = prescriptionRepository.findPrescriptionByPatient(pat);
        return lpres;
    }

    @Override
    public List<Prescription> getPrescriptionByMedecin(Medecin med) {
        List<Prescription> lpres = prescriptionRepository.findPrescriptionByMedecin(med);
        return lpres;
    }

}
