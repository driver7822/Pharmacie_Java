package be.condorcet.victorlorfevreprojet2;

import be.condorcet.victorlorfevreprojet2.entities.*;
import be.condorcet.victorlorfevreprojet2.services.MedecinServiceImpl;
import be.condorcet.victorlorfevreprojet2.services.MedicamentServiceImpl;
import be.condorcet.victorlorfevreprojet2.services.PatientServiceImpl;
import be.condorcet.victorlorfevreprojet2.services.PrescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class Victorlorfevreprojet2Application implements CommandLineRunner {

    /*@Autowired
    private PatientServiceImpl patientServiceImpl;

    @Autowired
    private MedecinServiceImpl medecinServiceImpl;

    @Autowired
    private MedicamentServiceImpl medicamentServiceImpl;

    @Autowired
    private PrescriptionServiceImpl prescriptionServiceImpl;*/

    public static void main(String[] args) {SpringApplication.run(Victorlorfevreprojet2Application.class, args);}

    @Override
    public void run(String... args) throws Exception {

        /*Patient pat = new Patient(null,"nss","Dubois","Joseph", Date.valueOf(LocalDate.now()),null);

        Medecin medecin = new Medecin(null,"matricule1","Dupont","Pierre","0478454643",null);

        Prescription pres = new Prescription(null,Date.valueOf(LocalDate.now()),null,null);

        Infos info = new Infos(null,5,null,null);

        Medicament medicament = new Medicament(null,"DAF09","Dafalgan","Contre les maux de tete et la fievre",new BigDecimal(4.0));

        try {
            System.out.println("Création du patient : \n"+pat);
            patientServiceImpl.create(pat);
        } catch (Exception e) {
            System.out.println("Erreur de création du patient : "+e);
        }

        try {
            System.out.println("Création du médecin : \n"+medecin);
            medecinServiceImpl.create(medecin);
        } catch (Exception e){
            System.out.println("Erreur de création du médecin : "+e);
        }

        try {
            System.out.println("Création du médicament : \n"+medicament);
            medicamentServiceImpl.create(medicament);
        } catch (Exception e){
            System.out.println("Erreur de création du médicament : "+e);
        }

        try {
            pres.setPatient(pat);
            pres.setMedecin(medecin);

            System.out.println("Prescription à créer : "+pres);
            prescriptionServiceImpl.create(pres);
            System.out.println("Prescription créer : "+pres);
        } catch (Exception e){
            System.out.println("Erreur de création de prescripiton : "+e);
        }

        try {
            info.setPrescription(pres);
            info.setMedicament(medicament);

            System.out.println("Info à créer : "+info);
        } catch (Exception e){
            System.out.println("Erreur de création d info : "+e);
        }*/

    }
}
