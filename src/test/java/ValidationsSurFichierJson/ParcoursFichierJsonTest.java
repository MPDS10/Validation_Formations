package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcoursFichierJsonTest {
    JSONObject presentation = new JSONObject();
    JSONObject cours = new JSONObject();
    JSONObject conference = new JSONObject();
    JSONObject colloque = new JSONObject();
    JSONObject atelier = new JSONObject();
    JSONObject seminaire = new JSONObject();
    JSONObject lectureDirigee = new JSONObject();
    JSONObject gpeDiscussion = new JSONObject();
    JSONObject redactionPro = new JSONObject();
    JSONObject projetDeRecherche = new JSONObject();
    JSONArray activites = new JSONArray();
    ParcoursFichierJson test = new ParcoursFichierJson(activites);



    @BeforeEach
    void setUp() {
        presentation.put("description", "Cours sur la déontologie");
        presentation.put("categorie", "présentation");
        presentation.put("heures", 4);
        presentation.put("date", "2022-02-02");
        activites.add(presentation);

        cours.put("description", "Cours sur la déontologie");
        cours.put("categorie", "cours");
        cours.put("heures", 12);
        cours.put("date", "2022-02-02");
        activites.add(cours);

        conference.put("description", "Cours sur la déontologie");
        conference.put("categorie", "conférence");
        conference.put("heures", 8);
        conference.put("date", "2022-02-02");
        activites.add(conference);

        colloque.put("description", "Cours sur la déontologie");
        colloque.put("categorie", "colloque");
        colloque.put("heures", 6);
        colloque.put("date", "2022-02-02");
        activites.add(colloque);

        atelier.put("description", "Cours sur la déontologie");
        atelier.put("categorie", "atelier");
        atelier.put("heures", 7);
        atelier.put("date", "2022-02-02");
        activites.add(atelier);

        seminaire.put("description", "Cours sur la déontologie");
        seminaire.put("categorie", "séminaire");
        seminaire.put("heures", 5);
        seminaire.put("date", "2022-02-02");
        activites.add(seminaire);

        lectureDirigee.put("description", "Cours sur la déontologie");
        lectureDirigee .put("categorie", "lecture dirigée");
        lectureDirigee .put("heures",14);
        lectureDirigee .put("date", "2022-02-02");
        activites.add(lectureDirigee );

        gpeDiscussion.put("description", "Cours sur la déontologie");
        gpeDiscussion.put("categorie", "groupe de discussion");
        gpeDiscussion.put("heures", 5);
        gpeDiscussion.put("date", "2022-02-02");
        activites.add(gpeDiscussion);

        redactionPro.put("description", "Cours sur la déontologie");
        redactionPro.put("categorie", "rédaction professionnelle");
        redactionPro.put("heures", 9);
        redactionPro.put("date", "2022-02-02");
        activites.add(redactionPro);

        projetDeRecherche.put("description", "Cours sur la déontologie");
        projetDeRecherche.put("categorie", "projet de recherche");
        projetDeRecherche.put("heures", 3);
        projetDeRecherche.put("date", "2022-02-02");
        activites.add(projetDeRecherche);



    }

    @AfterEach
    void tearDown() {
        activites = null;
        presentation = null;
        projetDeRecherche = null;
        redactionPro = null;
        colloque = null;
        conference = null;
        cours = null;
        gpeDiscussion = null;
        lectureDirigee = null;
        seminaire = null;

    }


    @Test
    void parcourirCategoriePresentation() {
        assertEquals(4,test.parcourirCategoriePresentation());
        assertNotEquals(2,test.parcourirCategoriePresentation());
    }

    @Test
    void parcourirCategorieGrpDiscussion() {
        assertEquals(5,test.parcourirCategorieGrpDiscussion());
        assertNotEquals(2,test.parcourirCategorieGrpDiscussion());
    }

    @Test
    void parcourirCategorieProjetRecherche() {
        assertEquals(3,test.parcourirCategorieProjetRecherche());
        assertNotEquals(2,test.parcourirCategorieProjetRecherche());
    }

    @Test
    void parcourirCategorieRedactionPro() {
        assertEquals(9,test.parcourirCategorieRedactionPro());
        assertNotEquals(3,test.parcourirCategorieRedactionPro());

    }

    @Test
    void parcourirCategorieCours() {
        assertEquals(10,test.parcourirCategorieCours());
        assertNotEquals(12,test.parcourirCategorieCours());
        assertNotEquals(3,test.parcourirCategorieCours());
    }

    @Test
    void parcourirCategorieAtelier() {
        assertEquals(7,test.parcourirCategorieAtelier());
        assertNotEquals(5,test.parcourirCategorieAtelier());
    }

    @Test
    void parcourirCategorieSeminaire() {
        assertEquals(5,test.parcourirCategorieSeminaire());
        assertNotEquals(3,test.parcourirCategorieSeminaire());
    }

    @Test
    void parcourirCategorieColloque() {
        assertEquals(6,test.parcourirCategorieColloque());
        assertNotEquals(9,test.parcourirCategorieColloque());
    }

    @Test
    void parcourirCategorieConference() {
        assertEquals(8,test.parcourirCategorieConference());
        assertNotEquals(5,test.parcourirCategorieConference());
    }

    @Test
    void parcourirCategorieLectureDirigee() {
        assertEquals(10,test.parcourirCategorieLectureDirigee());
        assertNotEquals(14,test.parcourirCategorieLectureDirigee());
    }
}
