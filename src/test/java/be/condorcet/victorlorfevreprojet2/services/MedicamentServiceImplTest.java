package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Medicament;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicamentServiceImplTest {

    @Autowired
    private MedicamentServiceImpl medicamentServiceImpl;

    private Medicament medicament;

    @BeforeEach
    void setUp() {
        try {
            medicament = new Medicament(null,"CodeTest","NomTest","DescriptionTest",new BigDecimal(5));
                medicamentServiceImpl.create(medicament);
            System.out.println("Création du médicament : "+medicament);
        }
        catch (Exception e){
            System.out.println("Erreur de création du médicament : "+medicament+" | Erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            medicamentServiceImpl.delete(medicament);
            System.out.println("Effacement du médicament : "+medicament);
        }
        catch (Exception e){
            System.out.println("Erreur d'effacement du médicament : "+medicament+" | Erreur : "+e);
        }
    }

    @Test
    void create() {assertNotEquals(0,medicament.getIdmedicament(),"id medicament non incrémenté");}

    @Test
    void creationDoublon(){
        Medicament medicament2 = new
                Medicament(null,"CodeTest","NomTest","DescriptionTest2",new BigDecimal(5));
                Assertions.assertThrows(Exception.class,()->{
                    medicamentServiceImpl.create(medicament2);
                },"création d'un doublon");
    }

    @Test
    void read() {
        try {
            int nummedicament = medicament.getIdmedicament();
            Medicament medicament2 = medicamentServiceImpl.read(nummedicament);
            assertEquals("CodeTest",medicament2.getCode(),"Codes différents "+"CodeTest"+" - "+medicament2.getCode());
            assertEquals("NomTest",medicament2.getNom(),"Noms différents "+"NomTest"+" - "+medicament2.getNom());
            assertEquals("DescriptionTest",medicament2.getDescription(),"Descriptions différents "+"DescriptionTest"+" - "+medicament2.getDescription());
            assertEquals(medicament2.getPrixunitaire(),new BigDecimal(5).setScale(2, RoundingMode.HALF_UP),"Prix unitaire différents "+medicament2.getPrixunitaire()+" - "+new BigDecimal(5));
        }
        catch (Exception e){
            fail("Recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try {
            medicament.setCode("CodeTest2");
            medicament.setNom("NomTest2");
            medicament.setDescription("DescriptionTest2");
            medicament.setPrixunitaire(new BigDecimal(3.2).setScale(2,RoundingMode.HALF_UP));

            medicament = medicamentServiceImpl.update(medicament);

            assertEquals("CodeTest2",medicament.getCode(),"Codes différents "+"CodeTest2"+" - "+medicament.getCode());
            assertEquals("NomTest2",medicament.getNom(),"Noms différents "+"NomTest2"+" - "+medicament.getNom());
            assertEquals("DescriptionTest2",medicament.getDescription(),"Descriptions différents "+"DescriptionTest2"+" - "+medicament.getDescription());
            assertEquals(medicament.getPrixunitaire(),new BigDecimal(3.2).setScale(2,RoundingMode.HALF_UP),"Prix unitaire différents "+medicament.getPrixunitaire()+" - "+new BigDecimal(3.2));
        }
        catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try {
            medicamentServiceImpl.delete(medicament);
            Assertions.assertThrows(Exception.class,() -> {
                medicamentServiceImpl.read(medicament.getIdmedicament());
            },"record non effacé");
        } catch (Exception e){
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void testRead() {
        List<Medicament> lmedicament = medicamentServiceImpl.read("NomTest");
        boolean trouve = false;
        for (Medicament m: lmedicament) {
            if (m.getNom().equals("NomTest")) trouve=true;
            else fail("un record ne correspond pas, nom = "+m.getNom());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void readCode() {
        List<Medicament> lmedicament = medicamentServiceImpl.readCode("CodeTest");
        boolean trouve = false;
        for(Medicament m : lmedicament){
            if(m.getCode().equals("CodeTest")) trouve=true;
            else fail("un record ne correspond pas, code = "+m.getCode());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }
}