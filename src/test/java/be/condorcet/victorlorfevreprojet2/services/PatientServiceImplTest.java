package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceImplTest {
    @Autowired
    private PatientServiceImpl patientServiceImpl;

    Patient pat;

    @BeforeEach
    void setUp() {
        try {
            pat = new Patient(null,"NssTest","NomTest","PrenomTest",  Date.valueOf(LocalDate.now()),null);
                patientServiceImpl.create(pat);
            System.out.println("Création du patient : "+pat);
        }
        catch (Exception e) {
            System.out.println("Erreur de création du client : "+pat+" | Erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            patientServiceImpl.delete(pat);
            System.out.println("Effacement du patient : "+pat);
        } catch (Exception e) {
            System.out.println("Erreur d'effacement du patient "+pat+" | Erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,pat.getIdPatient(),"ID du patient non incrémenté");
    }

    @Test
    void read() {
        try {
            int idPat = pat.getIdPatient();
            Patient pat2 = patientServiceImpl.read(idPat);
            assertEquals("NssTest",pat2.getNss(),"NSS différents "+"NssTest"+" - "+pat2.getNss());
            assertEquals("NomTest",pat2.getNom(),"Noms différents "+"NomTest"+" - "+pat2.getNom());
            assertEquals("PrenomTest",pat2.getNom(),"Prénoms différents "+"PrenomTest"+" - "+pat2.getPrenom());
        }
        catch (Exception e){
            fail("Recherche infructueuse : "+e);
        }
    }

    @Test
    void update() {
        try {
            pat.setNom("NomTest2");
            pat.setPrenom("PrenomTest2");
            pat.setNss("NssTest2");

            pat = patientServiceImpl.update(pat);

            assertEquals("NomTest2",pat.getNom(),"Nom différents "+"NomTest2"+" - "+pat.getNom());
            assertEquals("PrenomTest2",pat.getPrenom(),"Prénoms différents "+"PrenomTest2"+" - "+pat.getPrenom());
            assertEquals("NssTest2",pat.getNss(),"NSS différents "+"NssTest2"+" - "+pat.getNss());
        } catch (Exception e){
            fail("Erreur de mise à jour : "+e);
        }

    }

    @Test
    void delete() {
        try {
            patientServiceImpl.delete(pat);
            Assertions.assertThrows(Exception.class,() -> {
                patientServiceImpl.read(pat.getIdPatient());
            },"record non effacé");
        }
        catch (Exception e){
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void testRead() {
        List<Patient> lpat = patientServiceImpl.read("NomTest");
        boolean trouve=true;
        for (Patient p : lpat){
            if (p.getNom().equals("NomTest")) trouve=true;
            else fail("un record ne correspond pas, nom = "+pat.getNom());
            assertTrue(trouve,"record non trouvé dans la liste");
        }
    }
}