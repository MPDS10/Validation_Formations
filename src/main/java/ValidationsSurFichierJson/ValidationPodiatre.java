package ValidationsSurFichierJson;

import net.sf.json.JSONArray;

import java.time.DateTimeException;
import java.time.LocalDate;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.tableauErreurs;

public class ValidationPodiatre {

        /**
         * validerPodiatreCycle(cycleFormation)
         *
         * Cette methode s'assure que le seul cycle pour les podiatres est "2019-2022"
         *
         * @param cycleFormation
         *
         * @return Vrai si le cycle est supporté
         **/

        public static boolean validerPodiatreCycle(String cycleFormation)  {
            if(! cycleFormation.equals( POD_CYCLE_SUPPORTE )) {
                System.out.println(MSG_ERREUR_CYCLE_FORMATION);
                tableauErreurs.add(MSG_ERREUR_CYCLE_FORMATION) ;
                complet = false ;
            }
            return complet ;
        }

        /**
         * validerPodiatreHeuresDeFormation(heuresFormation)
         *
         * Valide les heures minimum de formation pour les podiatres
         *
         * @param heuresFormation

         */
        static boolean validerPodiatreHeuresDeFormation(int heuresFormation)  {
            if(heuresFormation < POD_MINIMUM_HEURES_DE_FORMATION  ){
                complet = false ;
                tableauErreurs.add(MSG_ERREUR_MIN_HEURE_DE_FORMATION_POD);
            }
            return heuresFormation >= POD_MINIMUM_HEURES_DE_FORMATION ;
         }

        /**
         * validerPodiatreDatesActivitesDeclarees()
         *
         * Cette methode s'assure que toutes les activites declarees pour les podiatres ont ete completee
         * entre le 1er juin 2019 et le 1er juin 2022 inclusivement .
         *
         *  @param  informationActivite
         */
         static JSONArray validerPodiatreDatesActivitesDeclarees(JSONArray informationActivite) {
            LocalDate date = null ;
            for (int i = 0; i < informationActivite.size(); i++) {
                try {
                    date = LocalDate.parse(informationActivite.getJSONObject(i).getString(CLE_DATE));
                }catch (DateTimeException e){tableauErreurs.add(MSG_ERR_DATE+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE));}
                if (date != null && !(date.compareTo(POD_DATE_MIN) >= 0 && date.compareTo(POD_DATE_MAX) <= 0 ||date.compareTo(DATE_AN_BISS_1) == 0))
                    informationActivite = ignorerCategorie(informationActivite,i) ;
            } return informationActivite ;
        }

        /**
         * validerPodiatreHeuresGroupeDiscussion(heuresGrpDiscussion)
         *
         * Valide les heures minimum de la categorie groupe de discussion poour l'ordre des podiatres
         *
         * @param heuresGrpDiscussion heures du groupe de discussion
         */
         static boolean validerPodiatreHeuresGroupeDiscussion(int heuresGrpDiscussion) {
            if(heuresGrpDiscussion < POD_MIN_HEURES_GROUPE_DISCUSSION){
                tableauErreurs.add(POD_MSG_ERREUR_MIN_HEURES_GROUPE_DISCUSSION);
            }
            return heuresGrpDiscussion < POD_MIN_HEURES_GROUPE_DISCUSSION ? false : true ;
        }

        /**
         * validerPodiatreHeuresCours(heuresGrpDiscussion)
         *
         * Valide les heures minimum de cours pour l'ordre des podiatres
         *
         * @param heuresCours heures de cours
         */
        static boolean validerPodiatreHeuresCours(int heuresCours) {
            if(heuresCours < POD_MIN_HEURES_COURS){
                tableauErreurs.add(POD_MSG_ERREUR_MIN_HEURES_COURS);
            }
            return heuresCours < POD_MIN_HEURES_COURS ? false : true ;
        }

        /**
         * validerPodiatreHeuresProjetRecherche(heuresProjetRecherche)
         *
         * Valide les heures minimum de la categorie projet de recherche pour l'ordre des podiatres
         *
         * @param heuresProjetRecherche heures du projet de recherche
         *
         */
        static boolean validerPodiatreHeuresProjetRecherche(int heuresProjetRecherche)  {
            if(heuresProjetRecherche < POD_MIN_HEURES_PROJET_RECHERCHE){
                tableauErreurs.add(POD_MSG_ERREUR_MIN_HEURES_PROJET_RECHERCHE);
            }
            return heuresProjetRecherche < POD_MIN_HEURES_PROJET_RECHERCHE ? false : true ;
        }
    }


