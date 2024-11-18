package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

class ValidationContraintesArchitectesTest {

    String cycleFormation1 ;
    String cycleFormation2 ;
    String cycleFormation3 ;
    String mauvaisCycle ;
    int heuresGrpDiscussion ;
    int heuresProjetRecherche ;
    int heuresTransferes ;
    int heureINvalide1;
    int heureInvalide2 ;
    JSONArray informationActivite =new JSONArray();
    JSONObject objetJson1 = new JSONObject();
    JSONObject objetJson2 =new JSONObject();
    JSONObject objetJson3 =new JSONObject();


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        cycleFormation1 = "2021-2023" ;
        cycleFormation2 = "2018-2020" ;
        cycleFormation3 = "2016-2018" ;
        mauvaisCycle = "2000-2002" ;
        heuresGrpDiscussion = 12 ;
        heuresTransferes = 4 ;
        heuresProjetRecherche = 22 ;
        informationActivite = new JSONArray() ;
        objetJson1 = new JSONObject() ;
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
        heureINvalide1 = 30 ;
        heureInvalide2 = -5 ;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        informationActivite = null ;
        cycleFormation1 = "" ;
        cycleFormation2 = "" ;
        cycleFormation3 = "" ;
        heuresTransferes = 0 ;
        heuresProjetRecherche = 0 ;
        heuresGrpDiscussion = 0 ;
        mauvaisCycle = "" ;
        objetJson1 = null ;
        objetJson2 = null ;
        objetJson3 = null ;
        heureINvalide1 = 0 ;
        heureInvalide2 = 0 ;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Cycle correct")
    void testValiderCycleArchitecte() {
        ValidationContraintesArchitectes.validerCycleArchitecte(cycleFormation3);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures du groupe de discussion valide")
    void test1ValiderHeuresGroupeDiscussion() {
        int resultat = ValidationContraintesArchitectes.validerHeuresGroupeDiscussion(heuresGrpDiscussion) ;
        Assertions.assertEquals(heuresGrpDiscussion,resultat) ;
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Heures du groupe de discussion invalide")
    void test2ValiderHeuresGroupeDiscussion() {
        int resultat = ValidationContraintesArchitectes.validerHeuresGroupeDiscussion(heureINvalide1) ;
        Assertions.assertEquals(17,resultat) ;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures du projet de recherche invalide")
    void test1ValiderHeuresProjetRecherche() {
        int resultat = ValidationContraintesArchitectes.validerHeuresProjetRecherche(heureINvalide1) ;
        Assertions.assertEquals(23,resultat) ;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures du projet de recherche valide")
    void test2ValiderHeuresProjetRecherche() {
        int resultat = ValidationContraintesArchitectes.validerHeuresProjetRecherche(heuresProjetRecherche) ;
        Assertions.assertEquals(heuresProjetRecherche,resultat) ;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures transferees du cycle valide")
    void test1ValiderHeuresTransferees() {
        int resultat = ValidationContraintesArchitectes.validerHeuresTransferees(heuresTransferes);
        Assertions.assertEquals(heuresTransferes,resultat);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures transferees du cycle null")
    void test2ValiderHeuresTransferees() {
        int resultat = ValidationContraintesArchitectes.validerHeuresTransferees(heureInvalide2);
        Assertions.assertEquals(0,resultat);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Heures transferees du cycle depassant bornes")
    void test3ValiderHeuresTransferees() {
        int resultat = ValidationContraintesArchitectes.validerHeuresTransferees(heureINvalide1);
        Assertions.assertEquals(7,resultat);
    }

    @org.junit.jupiter.api.Test
    void testValiderHeuresDeFormation() {
        Assertions.assertFalse(ValidationContraintesArchitectes.validerHeuresDeFormation(36,cycleFormation1));
        Assertions.assertFalse(ValidationContraintesArchitectes.validerHeuresDeFormation(28,mauvaisCycle));
    }

    @org.junit.jupiter.api.Test
    void testValiderHeuresDeFormation2() {
        Assertions.assertFalse(ValidationContraintesArchitectes.validerHeuresFormation2(2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Valider dates cycle 2021-2023")
    void testValiderDatesActivitesDeclareesCycleNormal() {
        JSONArray resultat = ValidationContraintesArchitectes.validerDatesActivitesDeclareesCycleNormal(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(1).getString("categorie"),resultat.getJSONObject(1).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(2).getString("categorie"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Valider dates cycle 2018-2020")
    void testValiderDatesActivitesDeclareesCycle1() {
        JSONArray resultat = ValidationContraintesArchitectes.validerDatesActivitesDeclareesCycle1(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(1).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(0).getString("categorie"),resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(2).getString("categorie"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Valider dates cycle 2016-2018")
    void testValiderDatesActivitesDeclareesCycle2() {
        JSONArray resultat = ValidationContraintesArchitectes.validerDatesActivitesDeclareesCycle2(informationActivite) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(2).getString("categorie"),resultat.getJSONObject(2).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(1).getString("categorie"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Valider dates cycle 2016-2018")
    void testValiderDatesActivitesDeclareesCycle(){
        JSONArray resultat = ValidationContraintesArchitectes.validerDatesActivitesDeclareesCycle(informationActivite,cycleFormation3) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(2).getString("categorie"),resultat.getJSONObject(2).getString("categorie"));
        Assertions.assertEquals("Ignored",resultat.getJSONObject(1).getString("categorie"));
    }

}