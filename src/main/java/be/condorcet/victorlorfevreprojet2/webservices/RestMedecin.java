package be.condorcet.victorlorfevreprojet2.webservices;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.services.MedecinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecins")
public class RestMedecin {

    @Autowired
    private MedecinServiceImpl medecinServiceImpl;

    //-------------------Retrouver le médecin correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Medecin> getMedecin(@PathVariable(value = "id")int id) throws Exception {
        System.out.println("Recherche du medecin d'id : "+id);
        Medecin medecin = medecinServiceImpl.read(id);
        return new ResponseEntity<>(medecin, HttpStatus.OK);
    }

    //-------------------Retrouver les médecin portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "nom={nom}",method = RequestMethod.GET)
    public ResponseEntity<List<Medecin>> listMedecinNom(@PathVariable(value = "nom")String nom) throws Exception{
        System.out.println("Recherche de "+nom);
        List<Medecin> medecins;
        medecins = medecinServiceImpl.read(nom);
        return new ResponseEntity<>(medecins,HttpStatus.OK);
    }

    //-------------------Créer un médecin--------------------------------------------------------
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Medecin> createMedecin(@RequestBody Medecin medecin) throws Exception{
        System.out.println("Création du médecin : "+medecin.getNom());
        medecinServiceImpl.create(medecin);
        return new ResponseEntity<>(medecin,HttpStatus.OK);
    }

    //-------------------Mettre à jour un médecin d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity<Medecin> majMedecin(@PathVariable(value = "id")int id,@RequestBody Medecin nouvMedecin) throws Exception{
        System.out.println("MAJ du médecin d'id : "+id);
        nouvMedecin.setIdmedecin(id);
        Medecin medmodifer = medecinServiceImpl.update(nouvMedecin);
        return new ResponseEntity<>(medmodifer,HttpStatus.OK);
    }

    //-------------------Effacer un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMedecin(@PathVariable(value = "id")int id)throws Exception{
        System.out.println("Effacement du médecin d'id : "+id);
        Medecin medecin = medecinServiceImpl.read(id);
        medecinServiceImpl.delete(medecin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
