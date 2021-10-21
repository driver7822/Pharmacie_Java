package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import be.condorcet.victorlorfevreprojet2.repositories.PatientRepository;
import be.condorcet.victorlorfevreprojet2.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class PrescriptionServiceImpl implements InterfPrescriptionService{
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    //@Autowired
   // private PatientRepository patientRepository;

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
        Integer id = prescription.getIdPrescription();
        Prescription oldPres = read(id);
        oldPres.setDatePrescription(prescription.getDatePrescription());
        prescriptionRepository.save(oldPres);
        return read(oldPres.getIdPrescription());
    }

    @Override
    public void delete(Prescription prescription) throws Exception{
        prescriptionRepository.deleteById(prescription.getIdPrescription());
    }

    @Override
    public List<Prescription> getPrescription(Patient pat){
        List<Prescription> lpres = prescriptionRepository.findPrescriptionsByPatient(pat);
        return lpres;
    }
}
