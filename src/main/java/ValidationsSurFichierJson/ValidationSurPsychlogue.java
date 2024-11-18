package ValidationsSurFichierJson;

import net.sf.json.JSONArray;

import java.time.DateTimeException;
import java.time.LocalDate;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.*;

public class ValidationSurPsychlogue {

    /**
     * valider cycles psychologues
     * @param cycleFormation
     */
     static boolean validerCyclePsy(String cycleFormation){
        if(!CYCLE_SUPPORTE_PSY.equals(cycleFormation)){
            System.out.println(MSG_ERREUR_CYCLE_FORMATION);
            tableauErreurs.add(MSG_ERREUR_CYCLE_FORMATION) ;
            complet = false ;
        }
        return complet ;
    }
    /**
     * validerDatesActivitesDeclareesPourPsychologue()
     *
     * Cette methode s'assure que toutes les activites declarees pour les psychologues ont ete completee
     * entre le 1er janvier 2018 et le 1er janvier 2023 inclusivement.
     *
     *  @param  informationActivite
     */
     static JSONArray validerDatesActivitesDeclareesPourPsychologue(JSONArray informationActivite) {
        LocalDate dateValidationPsyschologue = null ;
        for (int i = 0; i < informationActivite.size(); i++) {
            try {
                dateValidationPsyschologue = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));
            }catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
            if (dateValidationPsyschologue != null && !(dateValidationPsyschologue.compareTo(DATE_AN_BISS_1) == 0 || dateValidationPsyschologue.compareTo(DATE_MIN_PSYCHOLOGUE) >= 0 &&
                    dateValidationPsyschologue.compareTo(DATE_MAX_PSYCHOLOGUE) <= 0)) informationActivite = ignorerCategorie(informationActivite,i) ;
        } return informationActivite ;
    }

    /**
     * valide le cycle de formation des psychologues
     *
     * @param heuresFormationPsychologue
     * @return un nouveau tableau d'activites
     */
     static boolean validerHeuresDeFormationPsychologue(int heuresFormationPsychologue)  {
        if(heuresFormationPsychologue < NBR_HEURES_CYCLE_FORMATION_PSYCHOLOGUE ){
            complet = false ;
            tableauErreurs.add(MSG_ERREUR_HEURE_CYCLE_PSYCHOLOGUE);
        }
        return getComplet() == false ? false : true ;
    }

    /**
     * validerHeuresMinimumCours(heuresCours)
     *
     * Valide les heures minimum de la categorie Cours
     *
     * @param heuresCours heures de la redaction professionnelle
     * @return le nombre d'heure valide de la categorie Cours
     */
     static boolean validerHeuresMinimumCours(int heuresCours) {
        if(heuresCours < NBR_HEURES_MIN_DE_COURS){
            tableauErreurs.add(MESSAGE_ERREUR_HEURES_COURS);
        }
        return heuresCours < NBR_HEURES_MIN_DE_COURS ? false : true ;
    }

    /**
     * validerHeuresConference(heuresConference)
     *
     * Valide les heures maximum de la categorie Conference
     *
     * @param heuresConference
     * @return le nombre d'heure valide de la categorie Conference
     */
     static int validerHeuresConference(int heuresConference) {
        if(heuresConference > NB_MAX_HEURES_CONFERENCE){
            heuresConference = 0 ;
        }
        return heuresConference ;
    }
}
