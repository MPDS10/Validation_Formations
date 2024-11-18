package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidationContraintesFormationTest {
    JSONArray informationActivite =new JSONArray();
    JSONArray entete = new JSONArray() ;
    JSONObject objetJson1 = new JSONObject();
    JSONObject objetJson2 =new JSONObject();
    JSONObject objetJson3 =new JSONObject();
    JSONObject objetJson4 = new JSONObject();
    JSONObject object = new JSONObject() ;
    int heureCateg ;
    int heureValide ;
    int heureINvalide ;

    JSONArray liste = new JSONArray();
    JSONObject categ = new JSONObject();
    JSONObject decateg = new JSONObject();
    JSONObject obj = new JSONObject();

    JSONArray invalide = new JSONArray();
    JSONObject obz = new JSONObject() ;
    JSONObject numPermisPsy = new JSONObject();
    JSONObject numPermisGeo = new JSONObject();
    JSONObject numPermisGeoInv = new JSONObject();
    JSONObject numPermisPodiatre = new JSONObject();

    @BeforeEach
    void setUp() {
        objetJson1.put("description","Cours") ;
        objetJson1.put("categorie" , "cours") ;
        objetJson1.put("heures", 14) ;
        objetJson1.put( "date", "2019-02-02") ;
        objetJson2.put("description","Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans") ;
        objetJson2.put("categorie" , "groupe de discussion") ;
        objetJson2.put("heures", 20) ;
        objetJson2.put( "date", "2022-04-01") ;
        objetJson3.put("description","Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans") ;
        objetJson3.put("categorie" , "groupe de discussion") ;
        objetJson3.put("heures", 15) ;
        objetJson3.put( "date", "2017-06-01") ;
        objetJson4.put("numero_de_permis", "A0601") ;
        objetJson4.put( "cycle", "2021-2023") ;
        objetJson4.put( "ordre", "geologues") ;
        objetJson4.put( "nom", "BG") ;
        objetJson4.put( "prenom", "Sap") ;
        informationActivite.add(0, objetJson1);
        informationActivite.add(1,objetJson2);
        informationActivite.add(2,objetJson3);
        objetJson4.put("activites",informationActivite) ;
        heureValide = 10 ;
        heureINvalide = -5 ;
        heureCateg = 28 ;

        categ.put("categorie", "présentation");
        categ.put("categorie", "cours");
        categ.put("categorie", "conférence");
        liste.add(categ);

        decateg.put("description", "Cours sur la déontologie");
        decateg.put("categorie", "présentation");
        decateg.put("heures", 4);
        decateg.put("date", "2022-02-02");
        invalide.add(decateg);

        object.put("description","Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans") ;
        object.put("categorie" , "marchand") ;
        object.put("heures", 15) ;
        object.put( "date", "2017-06-01") ;

        obj.put("numero_de_permis", "A0601") ;
        obj.put( "cycle", "2018-2023") ;
        obj.put( "ordre", "geologues") ;
        obj.put("activites",informationActivite) ;

        obz.put("numero_de_permis", "A0601") ;
        obz.put( "cycle", "2019-2022") ;
        obz.put( "ordre", "geologues") ;
        obz.put("activites",informationActivite) ;

        numPermisPsy.put("numero_de_permis", "89765-13") ;

        numPermisGeo.put("nom", "Barth") ;
        numPermisGeo.put("prenom", "Diaz") ;
        numPermisGeo.put("numero_de_permis", "BD1234") ;
        numPermisGeo.put( "cycle", "2018-2023") ;
        numPermisGeo.put( "ordre", "geologues") ;
        numPermisGeo.put("activites",informationActivite) ;

        numPermisGeoInv.put("nom", "Pathe") ;
        numPermisGeoInv.put("prenom", "Sene") ;
        numPermisGeoInv.put("numero_de_permis", "SP1234") ;
        numPermisGeoInv.put( "cycle", "2019-2022") ;
        numPermisGeoInv.put( "ordre", "geologues") ;
        numPermisGeoInv.put("activites",informationActivite) ;



        numPermisPodiatre.put("numero_de_permis", "83453") ;

    }

    @AfterEach
    void tearDown() {
        objetJson1 = null ;
        objetJson2 = null ;
        objetJson3 = null ;
        objetJson4 = null ;
        informationActivite = null ;
        heureCateg = 0 ;
        categ = null;
        liste = null;
        object = null ;
        obj = null ;
        obz = null ;
        numPermisGeoInv = null ;
    }

    @Test
    void validerCategories() {
        boolean resultat = ValidationContraintesFormation.validerCategories(objetJson4.getJSONArray("activites")) ;
        assertTrue(resultat);
    }

    @Test
    @DisplayName("Test avec une categorie invalide")
    void validerCategoriesTest2() {
        objetJson4.getJSONArray("activites").add(object);
        boolean resultat = ValidationContraintesFormation.validerCategories(objetJson4.getJSONArray("activites")) ;
        assertFalse(resultat);
    }

    @Test
    void ignorerCategorie() {
        liste = ValidationsSurFichierJson.ValidationContraintesFormation.ignorerCategorie(liste,0);
        assertEquals("Ignored",liste.getJSONObject(0).getString("categorie"));
        assertNotEquals("aaa",liste.getJSONObject(0).getString("categorie"));

    }

    @Test
    void validerHeuresActivites() {
        int heures ;
        heures = ValidationsSurFichierJson.ValidationContraintesFormation.validerHeuresActivites(9,"cours");
        assertEquals(9,heures);
        assertNotEquals(12,heures);
    }

    @Test
    @DisplayName("Heure redaction invalide")
    void validerHeuresRedactionPro() {
        int res = ValidationContraintesFormation.validerHeuresRedactionPro(heureCateg) ;
        assertEquals(17,res);
    }

    @Test
    @DisplayName("Heure redaction valide")
    void validerHeuresRedactionProTest2() {
        int res = ValidationContraintesFormation.validerHeuresRedactionPro(heureValide) ;
        assertEquals(heureValide,res);
    }

    @Test
    @DisplayName("Heure presentation invalide")
    void validerHeuresMaxDePresentation() {
        int res = ValidationContraintesFormation.validerHeuresMaxDePresentation(heureCateg) ;
        assertEquals(23,res);
    }

    @Test
    @DisplayName("Heure presentation valide")
    void validerHeuresMaxDePresentationTest2() {
        int res = ValidationContraintesFormation.validerHeuresMaxDePresentation(heureValide) ;
        assertEquals(heureValide,res);
    }

    @Test
    @DisplayName("Heure GROUPE DE CATEGORIES valide")
    void validerHeuresDugpeDeCteg() {
        boolean res = ValidationContraintesFormation.validerHeuresDugpeDeCteg(heureCateg) ;
        assertTrue(res);
    }

    @Test
    @DisplayName("Heure GROUPE DE CATEGORIES INvalide")
    void validerHeuresDugpeDeCtegTest2() {
        boolean res = ValidationContraintesFormation.validerHeuresDugpeDeCteg(heureValide) ;
        assertFalse(res);
    }


    @Test
    @DisplayName("test numero permis Archi valide")
    void validerNumeroPermisArchi() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisArchi(objetJson4.getString("numero_de_permis")) ;
        assertTrue(valide);
    }

    @Test
    @DisplayName("test numero permis archi invalide")
    void validerNumeroPermisArchiTest2() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisArchi("TOTO") ;
        assertFalse(valide);
    }
    @Test
    @DisplayName("test numero permis psy valide")
    void validerNumeroPsy() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisPsy(numPermisPsy.getString("numero_de_permis")) ;
        assertTrue(valide);
    }
    @Test
    @DisplayName("test numero permis psy invalide")
    void validerNumeroPsyInv() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisPsy("TOTO");
        assertFalse(valide);
    }
    @Test
    @DisplayName("test numero permis Geo valide")
    void validerNumeroPermisGeo() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisGeo(numPermisGeo.getString("numero_de_permis"),numPermisGeo) ;
        assertTrue(valide);
    }
    @Test
    @DisplayName("test numero permis Geo invalide")
    void validerNumeroPermisGeoInv() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisGeo(numPermisGeoInv.getString("numero_de_permis"),numPermisGeo) ;
        assertFalse(valide);
    }
    @Test
    @DisplayName("test numero permis Podiatre valide")
    void validerNumeroPermisPodiatre() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisPodiatre(numPermisPodiatre.getString("numero_de_permis")) ;
        assertTrue(valide);
    }
    @Test
    @DisplayName("test numero permis Podiatre invalide")
    void validerNumeroPermisPodiatreInv() {
        boolean valide = ValidationContraintesFormation.validerNumeroPermisPodiatre("Toto");
        assertFalse(valide);
    }

    @Test
    void validerDescription() {
        JSONArray resultat = ValidationContraintesFormation.validerDescription(objetJson4.getJSONArray("activites")) ;
        Assertions.assertEquals("Ignored",resultat.getJSONObject(0).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(1).getString("categorie"),resultat.getJSONObject(1).getString("categorie"));
        Assertions.assertEquals(informationActivite.getJSONObject(2).getString("categorie"),resultat.getJSONObject(2).getString("categorie"));
    }

    @Test
    void validationArchitecte() {
        ValidationContraintesFormation.validerArchitecte(objetJson4.getJSONArray("activites"),0,"2021-2023");
    }

    @Test
    void validationGeologue() {
        ValidationContraintesFormation.validerGeologue(objetJson4.getJSONArray("activites"));
    }

    @Test
    void validationPsychologues() {
        ValidationContraintesFormation.validerGeologue(objetJson4.getJSONArray("activites"));
    }

    @Test
    void verifierSiHeuresTransfereesExiste() {
        assertTrue(ValidationContraintesFormation.verifierSiHeuresTransfereesExiste(objetJson4));
    }

    @Test
    void validerHeuresMinParJour() {
        int heuresMIn;
        heuresMIn = ValidationsSurFichierJson.ValidationContraintesFormation.validerHeuresMinParJour(12,"cours");
        assertEquals(10,heuresMIn);
        assertNotEquals(12,heuresMIn);
    }

    @Test
    void gererArchitectes() {
        boolean bool = ValidationContraintesFormation.gererArchitectes(objetJson4);
        assertFalse(bool);
    }

    @Test
    void gererArchitectesTest2() {
        objetJson4.put("heures_transferees_du_cycle_precedent",5) ;
        boolean bool = ValidationContraintesFormation.gererArchitectes(objetJson4);
        assertTrue(bool);
    }

    @Test
    void gererPsychologues() {
        ValidationContraintesFormation.gererPsychologues(obj);
    }

    @Test
    void gererGeologues() {
        ValidationContraintesFormation.gererGeologues(obz);
    }

    @Test
    void validerTout() {
    }

    @Test
    void validerStructureArchitecte() {
        boolean valide = ValidationContraintesFormation.validerStructureArchitecte(objetJson4) ;
        assertFalse(valide);
        objetJson4.put("heures_transferees_du_cycle_precedent",5) ;
        valide = ValidationContraintesFormation.validerStructureArchitecte(objetJson4) ;
        assertTrue(valide);
    }

    @Test
    void validerHeures() {
    }

    @Test
    void validerSuite() {
    }

    @Test
    void validerHeuresSpecifiqueArchitectes() {
    }

    @Test
    void validerHeuresSpecifiqueGeologues() {
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(objetJson4.getJSONArray("activites")) ;
        boolean valide = ValidationContraintesFormation.validerHeuresSpecifiqueGeologues(parcoursFichierJson) ;
        assertFalse(valide);
    }

    @Test
    void validerHeuresSpecifiquePsychologues() {
    }

    @Test
    void validerHeuresGrpCategorie() {
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(objetJson4.getJSONArray("activites")) ;
        boolean valide = ValidationContraintesFormation.validerHeuresGrpCategorie(parcoursFichierJson,0) ;
        assertFalse(valide);
    }

    @Test
    void validerHeuresTotalArchitectes() {
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(objetJson4.getJSONArray("activites")) ;
        boolean valide = ValidationContraintesFormation.validerHeuresTotalArchitectes(parcoursFichierJson,0,objetJson4.getString("cycle")) ;
        assertFalse(valide);
    }

    @Test
    void validerHeuresTotalGeologues() {
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(objetJson4.getJSONArray("activites")) ;
        boolean valide = ValidationContraintesFormation.validerHeuresTotalGeologues(parcoursFichierJson) ;
        assertFalse(valide);
    }

    @Test
    void validerHeuresTotalPsychologues() {
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(objetJson4.getJSONArray("activites")) ;
        boolean valide = ValidationContraintesFormation.validerHeuresTotalPsychologues(parcoursFichierJson) ;
        assertFalse(valide);
    }

    @Test
    void validerStructureCategoriesActivites() {
        boolean estValide;
        boolean pasValide;
        estValide = ValidationsSurFichierJson.ValidationContraintesFormation.validerStructureCategoriesActivites(liste);
        pasValide = ValidationsSurFichierJson.ValidationContraintesFormation.validerStructureCategoriesActivites(invalide);
        assertTrue(pasValide);
        assertFalse(estValide);
    }

    @Test
    @DisplayName("Section ordre presente")
    void validerStructureFichierOrdres() {
        boolean valide = ValidationContraintesFormation.validerStructureFichierOrdres(objetJson4);
        assertTrue(valide);
    }

    @Test
    @DisplayName("Section ordre non presente")
    void validerStructureFichierOrdresTest2() {
        objetJson4.remove("ordre") ;
        boolean valide = ValidationContraintesFormation.validerStructureFichierOrdres(objetJson4);
        assertFalse(valide);
    }
    @Test
    void validerSexeSelonNorme() {
        assertTrue( ValidationContraintesFormation.validerSexeSelonNorme(1)==1);
        assertTrue( ValidationContraintesFormation.validerSexeSelonNorme(2)==2);
        assertTrue( ValidationContraintesFormation.validerSexeSelonNorme(0)==0);
        assertTrue(ValidationContraintesFormation.validerSexeSelonNorme(8)==-1);




    }
}