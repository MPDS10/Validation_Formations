package Traitement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationPrincipaleTest {

    int nombreArgumentsValide ;

    @BeforeEach
    void setUp() {
        nombreArgumentsValide = 2 ;
    }

    @AfterEach
    void tearDown() {
        nombreArgumentsValide= 0 ;
    }

    @Test
    @DisplayName("Nb Args valide")
    void validerNbArguments() {
        boolean valide = ApplicationPrincipale.validerNbArguments(nombreArgumentsValide);
        assertTrue(valide);
    }

    @Test
    @DisplayName("Nb Args invalide")
    void validerNbArgumentsTest2() {
        boolean valide = ApplicationPrincipale.validerNbArguments(5);
        assertFalse(valide);
    }

}