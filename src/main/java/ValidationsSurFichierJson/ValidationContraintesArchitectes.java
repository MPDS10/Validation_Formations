package ValidationsSurFichierJson;

import net.sf.json.JSONArray;

import java.time.DateTimeException;
import java.time.LocalDate;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.*;

public class ValidationContraintesArchitectes {

    /**
     * Valider cycle aechitecte
     * @param cycleFormation
     */
    static boolean validerCycleArchitecte(String cycleFormation){
        if(! cycleFormation.equals( CYCLE_SUPPORTE ) && !cycleFormation.equals( CYCLE_SUPPORTE_ARCHITECTE_1 ) &&
                !cycleFormation.equals( CYCLE_SUPPORTE_ARCHITECTE_2 )) {
            System.out.println(MSG_ERREUR_CYCLE_FORMATION);
            tableauErreurs.add(MSG_ERREUR_CYCLE_FORMATION) ;
            complet = false ;
        }
        return complet ;
    }

   /**
     * validerHeuresGroupeDiscussion(heuresGrpDiscussion)
     *
     * Valide les heures maximum de la categorie groupe de discussion
     *
     * @param heuresGrpDiscussion heures du groupe de discussion
     * @return le nombre d'heure valide de la categorie groupe de discussion
     */
    static int validerHeuresGroupeDiscussion(int heuresGrpDiscussion) {
        if(heuresGrpDiscussion > NB_MAX_HEURES_GROUPE_DISCUSSION){
            heuresGrpDiscussion = NB_MAX_HEURES_GROUPE_DISCUSSION ;
        }
        return heuresGrpDiscussion ;
    }

    /**
     * validerHeuresProjetRecherche(heuresProjetRecherche)
     *
     * Valide les heures maximum de la categorie projet de recherche
     *
     * @param heuresProjetRecherche heures du projet de recherche
     * @return le nombre d'heure valide de la categorie projet de recherche
     */
    static int validerHeuresProjetRecherche(int heuresProjetRecherche)  {
        if(heuresProjetRecherche > NB_MAX_HEURES_PROJET_RECHERCHE){
            heuresProjetRecherche = NB_MAX_HEURES_PROJET_RECHERCHE ;
        }
        return heuresProjetRecherche ;
    }

   /**
     * validerHeuresTransferees( heuresTransferes )
     *
     * Valide que les heures Transferees ne sont pas negatifs ou superieurs à 7
     *
     * @param heuresTransferes heures de surplus
     * @return le nombre d'heures de surplus
     */
    static int validerHeuresTransferees(int heuresTransferes) {
        if (heuresTransferes < MIN_HEURES_SUPP){
            heuresTransferes = MIN_HEURES_SUPP;
            tableauErreurs.add(MSG_ERREUR_HEURE_DE_SURPLUS_NEGATIF);
        }else if(heuresTransferes > MAX_HEURES_SUPP){
            heuresTransferes = MAX_HEURES_SUPP;
            tableauErreurs.add(MSG_ERREUR_HEURE_DE_SURLUS_SUPERIEUR_A_7 );
        }
        return heuresTransferes;
    }

    /**
     * validationHeuresDeFormation(heuresFormation)
     *
     * Valide les heures minimum de formation (40h)
     *
     * @param heuresFormation
     * @param cycle

     */
    static boolean validerHeuresDeFormation(int heuresFormation , String cycle)  {
        if(heuresFormation < MINIMUM_HEURES_DE_FORMATION && cycle.equals(CYCLE_SUPPORTE)){
            complet = false ;
            tableauErreurs.add(MSG_ERREUR_MIN_HEURE_DE_FORMATION);
        }else{
            validerHeuresFormation2(heuresFormation);
        }
        return getComplet() == false ? false : true ;
    }

    /**
     * validationHeuresDeFormation(heuresFormation)
     *
     * Valide les heures minimum de formation (42h)
     *
     * @param heuresFormation
     */
    static boolean validerHeuresFormation2(int heuresFormation){
        if(heuresFormation < MINIMUM_HEURES_DE_FORMATION_2 ){
            complet = false ;
            tableauErreurs.add(MSG_ERREUR_MIN_HEURE_DE_FORMATION_2);
        }
        return getComplet() == false ? false : true ;
    }

    /**
     * validerActivitesDeclarees()
     *
     * Cette methode s'assure que toutes les activites declarees ont ete completee
     *
     * @param cycle
     * @return tableau activites avec bonnes valeurs
     */
    static JSONArray validerDatesActivitesDeclareesCycle(JSONArray informationActivite , String cycle){
        if(cycle.equals(CYCLE_SUPPORTE)) informationActivite = validerDatesActivitesDeclareesCycleNormal(informationActivite) ;
        else if (cycle.equals(CYCLE_SUPPORTE_ARCHITECTE_1)) informationActivite = validerDatesActivitesDeclareesCycle1(informationActivite) ;
        else if (cycle.equals(CYCLE_SUPPORTE_ARCHITECTE_2)) informationActivite = validerDatesActivitesDeclareesCycle2(informationActivite) ;
        return informationActivite ;
    }

    /**
     * validerActivitesDeclarees()
     *
     * Cette methode s'assure que toutes les activites declarees ont ete completee
     *
     *  @param  informationActivite
     */
    static JSONArray validerDatesActivitesDeclareesCycleNormal(JSONArray informationActivite) {
        LocalDate date = null ;
        for (int i = 0; i < informationActivite.size(); i++) {
            try { date = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));}
            catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
            if (date != null && !(date.compareTo(DATE_MIN_ARCHITECTE_C1) >= 0 && date.compareTo(DATE_MAX_ARCHITECTE_C1) <= 0))
                informationActivite = ignorerCategorie(informationActivite,i) ;
        }
        return informationActivite ;
    }

    /**
     * validerActivitesDeclarees()
     *
     * Cette methode s'assure que toutes les activites declarees ont ete completee
     *
     *  @param  informationActivite
     */
    static JSONArray validerDatesActivitesDeclareesCycle1(JSONArray informationActivite){
        LocalDate date = null ;
        for (int i = 0; i < informationActivite.size(); i++) {
            try { date = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));}
            catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
            if (date != null && !(date.compareTo(DATE_AN_BISS_1) == 0 || date.compareTo(DATE_MIN_ARCHITECTE_C2) >= 0
                    && date.compareTo(DATE_MAX_ARCHITECTE_C2) <= 0)) informationActivite = ignorerCategorie(informationActivite,i) ;
        }
        return informationActivite ;
    }

    /**
     * validerActivitesDeclarees()
     *
     * Cette methode s'assure que toutes les activites declarees ont ete completee
     *
     *  @param  informationActivite
     */
    static JSONArray validerDatesActivitesDeclareesCycle2(JSONArray informationActivite){
        LocalDate date = null ;
        for (int i = 0; i < informationActivite.size(); i++) {
            try { date = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));}
            catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
            if (date != null && !(date.compareTo(DATE_MIN_ARCHITECTE_C3) >= 0 &&
                    date.compareTo(DATE_MAX_ARCHITECTE_C3) <= 0)) informationActivite = ignorerCategorie(informationActivite,i) ;
        }
        return informationActivite ;
    }

}
