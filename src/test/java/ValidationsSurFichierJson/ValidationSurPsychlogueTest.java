package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidationSurPsychlogueTest {

    String cycleFormation ;
    String mauvaisCycle ;
    int heuresConferences ;
    int heuresDeFormation ;
    int heuresCours ;
    int heureInvalide1 ;
    int heureInvalide2 ;
    JSONArray informationActivite =new JSONArray();
    JSONObject objetJson1 = new JSONObject();
    JSONObject objetJson2 =new JSONObject();
    JSONObject objetJson3 =new JSONObject();

    @BeforeEach
    void setUp() {
        cycleFormation = "2018-2023" ;
        mauvaisCycle = "2002-2005" ;
        heuresConferences = 10 ;
        heureInvalide1 = 40 ;
        heureInvalide2 = 25 ;
        heuresDeFormation = 91 ;
        heuresCours = 27 ;
        objetJson1.put("description","Cours sur la déontologie") ;
        objetJson1.put("categorie" , "cours") ;
        objetJson1.put("heures", 14) ;
        objetJson1.put( "date", "2019-02-02") ;
        objetJson2.put("description","Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans") ;
        objetJson2.put("categorie" , "groupe de discussion") ;
        objetJson2.put("heures", 5) ;
        objetJson2.put( "date", "2022-04-01") ;
        objetJson3.put("description","Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans") ;
        objetJson3.put("categorie" , "groupe de discussion") ;
        objetJson3.put("heures", 5) ;
        objetJson3.put( "date", "2017-06-01") ;
        informationActivite.add(0, objetJson1);
        informationActivite.add(1,objetJson2);
        informationActivite.add(2,objetJson3);
    }

    @AfterEach
    void tearDown() {
        informationActivite = null ;
        cycleFormation = "" ;
        mauvaisCycle = "" ;
        heuresCours = 0 ;
        heureInvalide2 = 0 ;
        heureInvalide1 = 0 ;
        heuresConferences = 0 ;
        heuresDeFormation = 0 ;
        objetJson1 = null ;
        objetJson2 = null ;
        objetJson3 = null ;

    }

    @Test
    @DisplayName("Test dates declarees dans tableau activites avec une date invalide")
    void validerDatesActivitesDeclareesPourPsychologue() {
        JSONArray resultat = ValidationSurPsychlogue.validerDatesActivitesDeclareesPourPsychologue(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(2).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(0).getString("categorie"),resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(1).getString("categorie"),resultat.getJSONObject(1).getString("categorie"));
    }

    @Test
    @DisplayName("Test heure de formation superieur a 90")
    void validerHeuresDeFormationPsychologue() {
        boolean valide = ValidationSurPsychlogue.validerHeuresDeFormationPsychologue(heuresDeFormation) ;
        assertTrue(valide);
    }

    @Test
    @DisplayName("Test heure de formation inferieur a 90")
    void validerHeuresDeFormationPsychologueTest2() {
        boolean valide = ValidationSurPsychlogue.validerHeuresDeFormationPsychologue(heureInvalide1) ;
        assertFalse(valide);
    }

    @Test
    @DisplayName("Test heure minimum cours superieur a 15")
    void validerHeuresMinimumCours() {
        boolean valide = ValidationSurPsychlogue.validerHeuresMinimumCours(heuresCours) ;
        assertTrue(valide);
    }

    @Test
    @DisplayName("Test heure minimum cours inferieur a 15 ")
    void validerHeuresMinimumCoursTest2() {
        boolean valide = ValidationSurPsychlogue.validerHeuresMinimumCours(heuresConferences) ;
        assertFalse(valide);
    }

    @Test
    @DisplayName("Test heure de conference valide")
    void validerHeuresConference() {
        int resultat = ValidationSurPsychlogue.validerHeuresConference(heuresConferences) ;
        Assertions.assertEquals(heuresConferences,resultat);
    }

    @Test
    @DisplayName("Test heure de conference invalide")
    void validerHeuresConferenceTest2() {
        int resultat = ValidationSurPsychlogue.validerHeuresConference(heureInvalide1) ;
        Assertions.assertEquals(0,resultat);
    }

    @Test
    void validerCyclePsy() {
        ValidationSurPsychlogue.validerCyclePsy(cycleFormation);
    }
}