package ValidationsSurFichierJson;

import net.sf.json.JSONArray;

import java.time.DateTimeException;
import java.time.LocalDate;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.*;

public class ValidationGeologue {


    /**
     * validerGeoCycle(cycleFormation)
     * Cette methode s'assure que le seul cycle par les geologues est "2019-2022"
     *
     * @param cycleFormation
     *
     * @return Vrai si le cycle est supporté
     **/

     static boolean validerGeologueCycle(String cycleFormation)  {
        if(! cycleFormation.equals( GEO_CYCLE_SUPPORTE )) {
            System.out.println(MSG_ERREUR_CYCLE_FORMATION);
            tableauErreurs.add(MSG_ERREUR_CYCLE_FORMATION) ;
            complet = false ;
        }
        return complet ;
    }

    /**
     * validationGeoHeuresDeFormation(heuresFormation)
     *
     * Valide les heures minimum de formation pour les Géologues
     *
     * @param heuresFormation

     */
    static boolean validerGeologueHeuresDeFormation(int heuresFormation)  {
        if(heuresFormation < GEO_MINIMUM_HEURES_DE_FORMATION ){
            complet = false ;
            tableauErreurs.add(MSG_ERREUR_MIN_HEURE_DE_FORMATION_GEO);
        }
        return getComplet() == false ? false : true ;
    }

    /**
     * validerGeoDatesActivitesDeclarees()
     *
     * Cette methode s'assure que toutes les activites declarees pour les géologues ont ete completee
     * entre le 1er juin 2019 et le 1er juin 2022 inclusivement .
     *
     *  @param  informationActivite
     */
     static JSONArray validerGeologueDatesActivitesDeclarees(JSONArray informationActivite) {
        LocalDate date = null ;
        for (int i = 0; i < informationActivite.size(); i++) {
            try {
                date = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));
            }catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
            if (date != null && !(date.compareTo(GEO_DATE_MIN) >= 0 && date.compareTo(GEO_DATE_MAX) <= 0 ||date.compareTo(DATE_AN_BISS_1) == 0))
                informationActivite = ignorerCategorie(informationActivite,i) ;
        } return informationActivite ;
    }

    /**
     * validerGeoHeuresGroupeDiscussion(heuresGrpDiscussion)
     *
     * Valide les heures minimum de la categorie groupe de discussion
     *
     * @param heuresGrpDiscussion heures du groupe de discussion
     */
    static boolean validerGeologueHeuresGroupeDiscussion(int heuresGrpDiscussion) {
        if(heuresGrpDiscussion < GEO_MIN_HEURES_GROUPE_DISCUSSION){
            tableauErreurs.add(GEO_MSG_ERREUR_MIN_HEURES_GROUPE_DISCUSSION);
        }
        return heuresGrpDiscussion < GEO_MIN_HEURES_GROUPE_DISCUSSION ? false : true ;
    }

    /**
     * validerGeoHeuresCours(heuresGrpDiscussion)
     *
     * Valide les heures minimum de cours
     *
     * @param heuresCours heures de cours
     */
    static boolean validerGeologueHeuresCours(int heuresCours) {
        if(heuresCours < GEO_MIN_HEURES_COURS){
            tableauErreurs.add(GEO_MSG_ERREUR_MIN_HEURES_COURS);
        }
        return heuresCours < GEO_MIN_HEURES_COURS ? false : true ;
    }

    /**
     * validerHeuresProjetRecherche(heuresProjetRecherche)
     *
     * Valide les heures minimum de la categorie projet de recherche
     *
     * @param heuresProjetRecherche heures du projet de recherche
     *
     */
    static boolean validerGeologueHeuresProjetRecherche(int heuresProjetRecherche)  {
        if(heuresProjetRecherche < GEO_MIN_HEURES_PROJET_RECHERCHE){
            tableauErreurs.add(GEO_MSG_ERREUR_MIN_HEURES_PROJET_RECHERCHE);
        }
        return heuresProjetRecherche < GEO_MIN_HEURES_PROJET_RECHERCHE ? false : true ;
    }
}
