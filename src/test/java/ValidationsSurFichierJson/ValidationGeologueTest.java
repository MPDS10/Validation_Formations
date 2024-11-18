package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidationGeologueTest {
    String cycleFormation ;
    int heuresCours ;
    int heuresProjetDeRecherche;
    int heuresDeFormation ;
    int heuresDeFormationMin;
    int heuresGpeDeDisc ;
    int heureInvalide1 ;
    int heureInvalide2 ;
    JSONArray informationActivite =new JSONArray();
    JSONObject objetJson1 = new JSONObject();
    JSONObject objetJson2 =new JSONObject();
    JSONObject objetJson3 =new JSONObject();

    @BeforeEach
    void setUp() {
        cycleFormation = "2019-2022" ;
        heuresCours = 22 ;
        heureInvalide1 = 40 ;
        heureInvalide2 = 0 ;
        heuresDeFormation = 60 ;
        heuresDeFormationMin = 50;
        heuresGpeDeDisc = 27 ;
        heuresProjetDeRecherche = 4;
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
        heuresGpeDeDisc = 0 ;
        heureInvalide2 = 0 ;
        heureInvalide1 = 0 ;
        heuresCours = 0 ;
        heuresDeFormation = 0 ;
        heuresProjetDeRecherche = 0;
        objetJson1 = null ;
        objetJson2 = null ;
        objetJson3 = null ;

    }


    @Test
    void validerGeologueCycle() {
        ValidationGeologue.validerGeologueCycle(cycleFormation);
    }

    @Test
    @DisplayName("Test heure de formation inferieur a 55")
    void validerGeologueHeuresDeFormation() {
        boolean invalide = ValidationGeologue.validerGeologueHeuresDeFormation(heuresDeFormationMin);
        assertFalse(invalide);
    }

    @Test
    void validerGeologueDatesActivitesDeclarees() {
        JSONArray resultat = ValidationGeologue.validerGeologueDatesActivitesDeclarees(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(1).getString("categorie"),resultat.getJSONObject(1).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(2).getString("categorie"));
    }

    @Test
    @DisplayName("Test heure minimum GroupeDiscussion superieur a 1")
    void validerGeologueHeuresGroupeDiscussion() {
        boolean valide = ValidationGeologue.validerGeologueHeuresGroupeDiscussion(heuresGpeDeDisc) ;
        boolean invalide = ValidationGeologue.validerGeologueHeuresGroupeDiscussion(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);
    }

    @Test
    @DisplayName("Test heure minimum cours superieur a 22")
    void validerGeologueHeuresCours() {
        boolean valide = ValidationGeologue.validerGeologueHeuresCours(heuresCours) ;
        boolean invalide = ValidationGeologue.validerGeologueHeuresCours(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);
    }

    @Test
    @DisplayName("Test heure minimum Projet De Recherche superieur a 3")
    void validerGeologueHeuresProjetRecherche() {
        boolean valide = ValidationGeologue.validerGeologueHeuresProjetRecherche(heuresProjetDeRecherche) ;
        boolean invalide = ValidationGeologue.validerGeologueHeuresProjetRecherche(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);

    }



}

