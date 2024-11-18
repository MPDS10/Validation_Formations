package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationPodiatreTest {

    String cycleFormation ;
    String mauvaisCycle ;
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

        cycleFormation = "2018-2023" ;
        mauvaisCycle = "2002-2005" ;
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
        mauvaisCycle = "" ;
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
    void validerPodiatreCycle() {
    }

    @Test
    void validerPodiatreHeuresDeFormation1() {
        boolean invalide = ValidationPodiatre.validerPodiatreHeuresDeFormation(heuresDeFormationMin);
        assertFalse(invalide);
    }

    @Test
    void validerPodiatreHeuresDeFormation2(){
        boolean valide = ValidationPodiatre.validerPodiatreHeuresDeFormation(heuresDeFormation);
        assertTrue(valide);
    }

    @Test
    void validerPodiatreDatesActivitesDeclarees() {

        JSONArray resultat = ValidationPodiatre.validerPodiatreDatesActivitesDeclarees(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(1).getString("categorie"),resultat.getJSONObject(1).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(2).getString("categorie"));
    }

    @Test
    void validerPodiatreHeuresGroupeDiscussion() {
        boolean valide = ValidationPodiatre.validerPodiatreHeuresGroupeDiscussion(heuresGpeDeDisc) ;
        boolean invalide = ValidationPodiatre.validerPodiatreHeuresGroupeDiscussion(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);
    }

    @Test
    void validerPodiatreHeuresCours() {
        boolean valide = ValidationPodiatre.validerPodiatreHeuresCours(heuresCours) ;
        boolean invalide = ValidationPodiatre.validerPodiatreHeuresCours(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);
    }

    @Test
    void validerPodiatreHeuresProjetRecherche() {
        boolean valide = ValidationPodiatre.validerPodiatreHeuresProjetRecherche(heuresProjetDeRecherche) ;
        boolean invalide = ValidationPodiatre.validerPodiatreHeuresProjetRecherche(heureInvalide2);
        assertTrue(valide);
        assertFalse(invalide);
    }
}