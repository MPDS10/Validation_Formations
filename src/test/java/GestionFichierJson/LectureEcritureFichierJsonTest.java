package GestionFichierJson;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static GestionFichierJson.LectureEcritureFichierJson.*;

class LectureEcritureFichierJsonTest {

    LectureEcritureFichierJson testLecture1 = new LectureEcritureFichierJson("","");
    ArrayList<String> tableauTest = new ArrayList<>();
    boolean estValide ;
    JSONObject objetTest;
    @BeforeEach
    void setUp() throws IOException {
        testLecture1.lireFichierJson();
        tableauTest.add("test1");
        tableauTest.add("test2");
        tableauTest.add("test3");
        estValide = true;
    }
    @AfterEach
    void tearDown() {
        tableauTest = null;
        estValide = false;

    }



    @Test
    void lireFichierJson() {
        assertFalse(testLecture1.lireFichierJson());
    }

    @Test
    void chargerInformationFichier() {

    }

    @Test
    void ajouterInformationsRetour() {
        Assertions.assertNotEquals(tableauTest,testLecture1.ajouterInformationsRetour(estValide,tableauTest));
    }

    @Test
    void   ecrireDansFichierJson() {

        assertFalse(LectureEcritureFichierJson.ecrireDansFichierJson(true,tableauTest));

    }


}

