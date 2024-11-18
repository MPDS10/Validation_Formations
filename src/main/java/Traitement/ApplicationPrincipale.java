package Traitement;

import GestionFichierJson.LectureEcritureFichierJson;

import java.util.ArrayList;

import static ValidationsSurFichierJson.ValidationContraintesFormation.*;

/**
 * Classe Traitement.ApplicationPrincipale
 *
 * Cette classe permet de manipuler les autres classes et de
 * les tester.
 *
 * @author Ekoue Andy Scott Messan
 * @author Sow Mame Penda Dieng
 * @author Gueye Fatou
 * @author Gicemuzi Armand-Donnel
 */
public class ApplicationPrincipale {

    static boolean validerNbArguments(int nombreArguments){
        if(nombreArguments != 2){
            System.out.println("Erreur, nombre d'arguments invalide!.\n\nEssayez plutôt avec deux arguments, "+
                    "le premier étant le chemin du fichier Json à valider et le second, \nle nom ou le chemin du"+
                    " fichier Json de sortie.") ;
        }
        return nombreArguments == 2 ;
    }

    public static void main(String[] args) {
        if(!validerNbArguments(args.length)) System.exit(1); ;
        LectureEcritureFichierJson lecteurFichierJson = new LectureEcritureFichierJson(args[0], args[1]) ;
        if(lecteurFichierJson.lireFichierJson()){
            validerTout(lecteurFichierJson.getObjetJson());
            boolean complet = getComplet();
            ArrayList<String> tableauErreurs = getTableauErreurs();
            lecteurFichierJson.ecrireDansFichierJson(complet, tableauErreurs);
        }
    }

}


