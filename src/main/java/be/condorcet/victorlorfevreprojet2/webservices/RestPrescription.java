package be.condorcet.victorlorfevreprojet2.webservices;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import be.condorcet.victorlorfevreprojet2.services.MedecinServiceImpl;
import be.condorcet.victorlorfevreprojet2.services.PatientServiceImpl;
import be.condorcet.victorlorfevreprojet2.services.PrescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/prescriptions")
public class RestPrescription {

    @Autowired
    private PrescriptionServiceImpl prescriptionServiceImpl;

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @Autowired
    private MedecinServiceImpl medecinServiceImpl;

    //-------------------Retrouver la prescription correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Prescription> getPrescription(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Recerche de la prescription d'id : "+id);
        Prescription prescription = prescriptionServiceImpl.read(id);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    //-------------------Retrouver la prescription correspondant à un id de patient donné--------------------------------------------------------
    @RequestMapping(value = "/idpatient={id}",method = RequestMethod.GET)
    public ResponseEntity<List<Prescription>> getPrescriptionPatient(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Recherche des prescriptions du patient d'id : "+id);
        Patient patient = patientServiceImpl.read(id);
        List<Prescription> lpres = prescriptionServiceImpl.getPrescriptionByPatient(patient);
        return new ResponseEntity<>(lpres, HttpStatus.OK);
    }

    //-------------------Retrouver la prescription correspondant à un id de medecin donné--------------------------------------------------------
    @RequestMapping(value = "/idmedecin={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Prescription>> getPrescriptionMedecin(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Recherche des prescriptions du medecin d'id : "+id);
        Medecin medecin = medecinServiceImpl.read(id);
        List<Prescription> lpres = prescriptionServiceImpl.getPrescriptionByMedecin(medecin);
        return new ResponseEntity<>(lpres, HttpStatus.OK);
    }

    //-------------------Créer une prescription--------------------------------------------------------
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription pres) throws Exception{
        System.out.println("Création de la prescription du patient "+pres.getPatient());
        prescriptionServiceImpl.create(pres);
        return new ResponseEntity<>(pres,HttpStatus.OK);
    }

    //-------------------Mettre à jour une prescription d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Prescription> majPrescription(@PathVariable(value = "id")int id,@RequestBody Prescription nouvpres) throws Exception{
        System.out.println("MAJ de la prescription n° "+id);
        nouvpres.setIdprescription(id);
        Prescription cpres = prescriptionServiceImpl.update(nouvpres);
        return new ResponseEntity<>(cpres,HttpStatus.OK);
    }

    //-------------------Effacer une prescription d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePrescription(@PathVariable(value = "id")int id) throws Exception {
        System.out.println("Effacement de la prescription n° "+id);
        Prescription pres = prescriptionServiceImpl.read(id);
        prescriptionServiceImpl.delete(pres);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/idpatient={idpat}/idmedecin={idmed}", method = RequestMethod.GET)
    public ResponseEntity<List<Prescription>> getPrescriptionPatientAndMedecin(@PathVariable(value = "idpat")int idpat, @PathVariable(value = "idmed")int idmed) throws Exception{
        System.out.println("Affichage des prescription du patient d'id "+idpat+" et du medecin d'id "+idmed);
        Patient patient = patientServiceImpl.read(idpat);
        Medecin medecin = medecinServiceImpl.read(idmed);

        List<Prescription> lpres = prescriptionServiceImpl.getPrescriptionByPatientAndMedecin(patient,medecin);

        return new ResponseEntity<>(lpres, HttpStatus.OK);
    }

    @RequestMapping(value = "/idpatient={id}/orderByNomMedecin", method = RequestMethod.GET)
    public ResponseEntity<List<Prescription>> getPrescriptionPatientOrderByNomMedecin(@PathVariable(value="id")int id) throws Exception {
        System.out.println("Affichage des prescription du patient d'id "+id+" tiree par le nom du medecin");
        Patient patient = patientServiceImpl.read(id);

        List<Prescription> lpres = prescriptionServiceImpl.getPrescriptionByPatientOrderByNomMedecin(patient);

        return new ResponseEntity<>(lpres, HttpStatus.OK);
    }


    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}
