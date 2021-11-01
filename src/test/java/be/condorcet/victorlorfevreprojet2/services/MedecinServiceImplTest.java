package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedecinServiceImplTest {

    @Autowired
    private MedecinServiceImpl medecinServiceImpl;

    private Medecin medecin;
    @BeforeEach
    void setUp() {
        try {
            medecin = new Medecin(null,"MatriculeTest","NomTest","PrenomTest","0479123456",null);
                medecinServiceImpl.create(medecin);
            System.out.println("Création du médecin : "+medecin);
        }
        catch (Exception e){
            System.out.println("Erreur de création du médecin : "+medecin+" | Erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            medecinServiceImpl.delete(medecin);
            System.out.println("Effacement du médecin : "+medecin);
        }
        catch (Exception e) {
            System.out.println("Erreur d'effacement du médecin : "+medecin+" | Erreur : "+e);
        }
    }

    @Test
    void create() { assertNotEquals(0,medecin.getIdmedecin(),"id medecin non incrémenté");}

    @Test
    void read() {
        try {
            int nummedecin=medecin.getIdmedecin();
            Medecin medecin2=medecinServiceImpl.read(nummedecin);
            assertEquals("MatriculeTest",medecin2.getMatricule(),"Matricules différents "+"MatriculeTest"+" - "+medecin2.getMatricule());
            assertEquals("NomTest",medecin2.getNom(),"Noms différents "+"NomTest"+" - "+medecin2.getNom());
            assertEquals("PrenomTest",medecin2.getPrenom(),"Prénoms différents "+"PrenomTest"+" - "+medecin2.getPrenom());
        }
        catch (Exception e){
            fail("Recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try {
            medecin.setMatricule("MatriculeTest2");
            medecin.setNom("NomTest2");
            medecin.setPrenom("PrenomTest2");

            medecin = medecinServiceImpl.update(medecin);

            assertEquals("MatriculeTest2",medecin.getMatricule(),"Matricules différents "+"MatriculeTest2"+" - "+medecin.getMatricule());
            assertEquals("NomTest2",medecin.getNom(),"Noms différents "+"NomTest2"+" - "+medecin.getNom());
            assertEquals("PrenomTest2",medecin.getPrenom(),"Prénoms différents "+"PrenomTest2"+" - "+medecin.getPrenom());

        } catch (Exception e) {
            fail("Erreur de mise à jour : "+e);
        }
    }

    @Test
    void delete() {
        try {
            medecinServiceImpl.delete(medecin);
            Assertions.assertThrows(Exception.class,() -> {
               medecinServiceImpl.read(medecin.getIdmedecin());
            },"record non effacé");
        } catch (Exception e){
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void testRead() {
        List<Medecin> lmedecin = medecinServiceImpl.read("NomTest");
        boolean trouve = false;
        for (Medecin m : lmedecin){
            if(m.getNom().equals("NomTest")) trouve=true;
            else fail("un record ne correspond pas, nom = "+m.getNom());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void readMatricule() {
        List<Medecin> lmedecin = medecinServiceImpl.readMatricule("MatriculeTest");
        boolean trouve = false;
        for (Medecin m : lmedecin){
            if (m.getMatricule().equals("MatriculeTest")) trouve=true;
            else fail("un record ne correspond pas, matricule = "+m.getMatricule());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }
}