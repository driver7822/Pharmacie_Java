package be.condorcet.victorlorfevreprojet2.webservices;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.services.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class RestPatient {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    //-------------------Retrouver le patient correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Recherche du patient d'id : "+id);
        Patient patient = patientServiceImpl.read(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    //-------------------Retrouver le patient correspondant à un nss donné--------------------------------------------------------
    @RequestMapping(value = "/nss={nss}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable(value = "nss") String nss) throws Exception {
        System.out.println("Recherche du patient avec NSS : "+nss);
        Patient patient= patientServiceImpl.readNss(nss);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    //-------------------Retrouver les patients portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listPatientNom(@PathVariable(value = "nom") String nom) throws Exception {
        System.out.println("Recherche de "+nom);
        List<Patient> patients;
        patients = patientServiceImpl.read(nom);
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    //-------------------Créer un patient--------------------------------------------------------
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws Exception {
        System.out.println("Création du patient "+patient.getNom());
        patientServiceImpl.create(patient);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    //-------------------Mettre à jour un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity<Patient> majPatient(@PathVariable(value = "id")int id, @RequestBody Patient nouvpatient) throws Exception {
        System.out.println("MAJ du patient id : "+id);
        nouvpatient.setIdpatient(id);
        Patient patmodifer = patientServiceImpl.update(nouvpatient);
        return new ResponseEntity<>(patmodifer,HttpStatus.OK);
    }

    //-------------------Effacer un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePatient(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Effacement du patient d'id : "+id);
        Patient patient = patientServiceImpl.read(id);
        patientServiceImpl.delete(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}
