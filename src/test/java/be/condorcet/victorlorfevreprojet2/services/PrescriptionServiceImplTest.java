package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medecin;
import be.condorcet.victorlorfevreprojet2.entities.Patient;
import be.condorcet.victorlorfevreprojet2.entities.Prescription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrescriptionServiceImplTest {
    private Medecin medecin;

    private Patient patient;

    private Prescription prescription;

    @Autowired
    private MedecinServiceImpl medecinServiceImpl;

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @Autowired
    private PrescriptionServiceImpl prescriptionServiceImpl;

    @BeforeEach
    void setUp() {
        try {
            patient = new Patient(null,"NssTest","NomTest","PrenomTest",  Date.valueOf(LocalDate.now()),null);
                patientServiceImpl.create(patient);
            System.out.println("Création du Patient : "+patient);

            medecin = new Medecin(null,"MatriculeTest","NomTest","PrenomTest","0479123456",null);
                medecinServiceImpl.create(medecin);
            System.out.println("Création du Médecin : "+medecin);

            prescription = new Prescription(null,Date.valueOf(LocalDate.now()),patient,medecin);
                prescriptionServiceImpl.create(prescription);
            System.out.println("Création de la prescription : "+prescription);
        } catch (Exception e){
            System.out.println("Erreur de création de la prescription : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            prescriptionServiceImpl.delete(prescription);
        } catch (Exception e){
            System.out.println("Erreur d'effacement de la prescription : "+e);
        }

        try {
            medecinServiceImpl.delete(medecin);
        } catch (Exception e){
            System.out.println("Erreur d'effacement du medecin : "+e);
        }

        try {
            patientServiceImpl.delete(patient);
        } catch (Exception e){
            System.out.println("Erreur d'effacement du patient : "+e);
        }

    }

    @Test
    void create() { assertNotEquals(0,prescription.getIdprescription(),"id prescription non incrémenté");}

    @Test
    void read() {
        try {
            int numpres = prescription.getIdprescription();
            Prescription prescription2 = prescriptionServiceImpl.read(numpres);

            assertEquals(Date.valueOf(LocalDate.now()),prescription2.getDateprescription(),"Dates différentes : "+Date.valueOf(LocalDate.now())+" - "+prescription2.getDateprescription());

        } catch (Exception e){
            fail("recherche infructeuse "+e);
        }
    }

    @Test
    void update() {
        try {
            prescription.setDateprescription(Date.valueOf(LocalDate.now().plusYears(1)));

            prescription = prescriptionServiceImpl.update(prescription);

            assertEquals(Date.valueOf(LocalDate.now().plusYears(1)),prescription.getDateprescription(),"Date prescription différents "+Date.valueOf(LocalDate.now().plusYears(1))+" - "+prescription.getDateprescription());
        } catch (Exception e){
            System.out.println("Erreur de mise à jour : "+e);
        }
    }

    @Test
    void delete() {
        try {
            prescriptionServiceImpl.delete(prescription);
            Assertions.assertThrows(Exception.class,() -> {
                prescriptionServiceImpl.read(prescription.getIdprescription());
            },"record non effacé");
        }
        catch (Exception e){
            fail("erreur d'effacement : "+e);
        }
    }


    @Test
    void getPrescriptionByPatient() {
        try {
            Collection<Prescription> lpres = prescriptionServiceImpl.getPrescriptionByPatient(patient);
            boolean trouve = false;
            for (Prescription p : lpres){
                if(p.getIdprescription()==prescription.getIdprescription()){
                    trouve=true;
                    break;
                }
            }
            assertTrue(trouve,"prescription absente de la liste du patient");
        }
        catch (Exception e){
            fail("Erreur de recheche "+e);
        }
    }
}