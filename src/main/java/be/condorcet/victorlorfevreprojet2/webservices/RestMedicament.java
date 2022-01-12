package be.condorcet.victorlorfevreprojet2.webservices;

import be.condorcet.victorlorfevreprojet2.entities.Medicament;
import be.condorcet.victorlorfevreprojet2.services.MedicamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/medicaments")
public class RestMedicament {

    @Autowired
    private MedicamentServiceImpl medicamentServiceImpl;

    //-------------------Retrouver le médicament correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Medicament> getMedicament(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Recherche du médicament d'id : "+id);
        Medicament medicament = medicamentServiceImpl.read(id);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }

    //-------------------Retrouver les médicaments portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Medicament>> listMedicamentNom(@PathVariable(value = "nom")String nom) throws Exception{
        System.out.println("Recherche de "+nom);
        List<Medicament> medicaments;
        medicaments = medicamentServiceImpl.read(nom);
        return new ResponseEntity<>(medicaments,HttpStatus.OK);
    }

    //-------------------Créer un médicament--------------------------------------------------------
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Medicament> createMedicament(@RequestBody Medicament medicament) throws Exception{
        System.out.println("Création du médicament "+medicament.getNom());
        medicamentServiceImpl.create(medicament);
        return new ResponseEntity<>(medicament,HttpStatus.OK);
    }

    //-------------------Mettre à jour un médicament d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Medicament> majMedicament(@PathVariable(value = "id")int id, @RequestBody Medicament nouvmedicament) throws Exception{
        System.out.println("MAJ du médicament id : "+id);
        nouvmedicament.setIdmedicament(id);
        Medicament medmodifier = medicamentServiceImpl.update(nouvmedicament);
        return new ResponseEntity<>(medmodifier,HttpStatus.OK);
    }

    //-------------------Effacer un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Medicament> deleteMedicament(@PathVariable(value = "id")int id)throws Exception{
        System.out.println("Effacement du médicament d'id : "+id);
        Medicament medicament = medicamentServiceImpl.read(id);
        medicamentServiceImpl.delete(medicament);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
