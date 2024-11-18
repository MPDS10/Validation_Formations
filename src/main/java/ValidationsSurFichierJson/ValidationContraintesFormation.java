package ValidationsSurFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesArchitectes.*;
import static ValidationsSurFichierJson.ValidationGeologue.*;
import static ValidationsSurFichierJson.ValidationSurPsychlogue.*;
import static ValidationsSurFichierJson.ValidationPodiatre.*;

/**
 * Validations.ValidationContraintesFormation.java
 *
 * Cette classe permet de valider toutes les contraintes liees a la formation.
 *
 * @author Ekoue Andy Scott Messan
 * @author Sow Mame Penda Dieng
 * @author Gueye Fatoulapi
 * @author Gicemuzi Armand-Donnel
 */
public class ValidationContraintesFormation {

    //Attribut de classe
    static ArrayList<String> tableauErreurs = new ArrayList<>();
    static boolean complet = true ;

    /**
     * getComplet()
     *
     * Retourne le booleen complet
     *
     * @return complet
     */
    public static boolean getComplet() {
        return complet;
    }

    /**
     * getTableauErreurs()
     *
     * Retourne le tableau contenant les erreurs
     *
     * @return le tableau contant les erreurs rencontrees lors de la validation
     */
    public static ArrayList<String> getTableauErreurs() {
        return tableauErreurs;
    }

    /**
     * validerSexeSelonNorme(int sexe)
     * Cette Methode verifie si le sexe respecte la norme ISO5218
     * sexe masculin = 1
     * sexe feminin = 2
     * sexe inconnu = 0
     *
     * @param sexe
     */
    static int validerSexeSelonNorme(int sexe){
        if(sexe < SEXE_INCONNU || sexe > SEXE_FEMININ){
            tableauErreurs.add(MSG_ERREUR_SEXE);
            sexe= -1;
        }
        return sexe;
    }

    /**
     * validerHeuresActivites(heuresActivites)
     *
     * Valide que les heures des activités sont valides
     *
     * @param heuresActivites heures d'activités
     * @return le nombre d'heure d'activités si valides
     */
    static int validerHeuresActivites(int heuresActivites, String nomCategorie) {
        if((""+heuresActivites).matches("[+-]?\\d*(\\.\\d+)?")&&heuresActivites!=0) {
            if (heuresActivites < LIMITE_MIN_HEURES_ACTIVITES) {
                tableauErreurs.add(MESSAGE_ERREUR_HEURE_ACTIVITE +nomCategorie );
                heuresActivites = 0 ;
            }
        }
        return heuresActivites;
    }

    /**
     * validerCategories(informationActivite)
     * Cette Methode s'assure que toutes les activités doivent
     * appartenir à une des catégories reconnues
     *
     * @param informationActivite
     * @return true quand toutes les categories sont bonnes
     */
    static boolean validerCategories(JSONArray informationActivite ) {
        boolean valide = true ;
        for (int i = 0; i < informationActivite.size(); i++) {
            if(!informationActivite.getJSONObject(i).getString(CLE_CATEGORIE).equals("Ignored")) {
                String categorie = informationActivite.getJSONObject(i).getString(CLE_CATEGORIE);
                if (!(categories.contains(categorie))) {
                    tableauErreurs.add(MSG_ERR_CATEGORIES_1 + categorie + MSG_ERR_CATEGORIES_2);
                    informationActivite.remove(i) ; valide = false ;}
            }
        } return valide;
    }

    /**
     * Permet d'ignorer une categorie des calculs
     *
     * @param activites le tableau d'activite
     * @param index de l'activite a ignorer
     * @return le nouveau tableau
     */
    static JSONArray ignorerCategorie(JSONArray activites,int index){
        tableauErreurs.add( MSG_ERR_DATE_ACTIVITE_1 + activites.getJSONObject(index).getString(CLE_CATEGORIE) +
                MSG_ERR_DATE_ACTIVITE_2 );
        activites.getJSONObject(index).put(CLE_CATEGORIE,"Ignored") ;
        return activites ;
    }

    /**
     * validerHeuresRedactionPro(heuresRedactionPro)
     *
     * Valide les heures maximum de la categorie redaction professionnelle
     *
     * @param heuresRedactionPro heures de la redaction professionnelle
     * @return le nombre d'heure valide de la categorie redaction professionnelle
     */
    static int validerHeuresRedactionPro(int heuresRedactionPro) {
        if(heuresRedactionPro > NB_MAX_HEURES_REDACTION_PRO){
            heuresRedactionPro = NB_MAX_HEURES_REDACTION_PRO ;
        }
        return heuresRedactionPro ;
    }

    /**
     * validerHeuresMaxDePresentation(heuresPresentation)
     *
     * Valide les heures maximum de la categorie présentation
     *
     * @param heuresPresentation heures de la présentation
     * @return le nombre d'heure valide de la categorie présentation
     */
    static int validerHeuresMaxDePresentation(int heuresPresentation)  {
        if(heuresPresentation > NBR_HEURES_MAX_DE_PRESENTATION){
            heuresPresentation=NBR_HEURES_MAX_DE_PRESENTATION;
        }
        return heuresPresentation;
    }

    /**
     * ValidationHeuresDugpeDeCteg(heuresCateg)
     *
     * Valide les heures minimum d'une groupe de Categorie
     *
     * @param heuresCateg les heures comptés
     */
    static boolean validerHeuresDugpeDeCteg(int heuresCateg) {
        if(heuresCateg < MINIMUM_HEURES_DES_CATEGORIES){
            tableauErreurs.add(NBR_HEURES_MIN_DU_GPE_DE_CATEGORIE);
        }
        return heuresCateg < MINIMUM_HEURES_DES_CATEGORIES ? false : true ;
    }

    /**
     * validerNumeroPermisArchi(numeroPermisDuMembre)
     * Cette methode s'assure que e numéro de permis doit être une lettre majuscule suivie de 4 chiffres. La lettre doit être A, T.
     *
     * @param numeroPermis
     *
     * @return validerNumeroPermisArchi
     */
    static boolean validerNumeroPermisArchi(String numeroPermis)  {
        String regex = "^[AT]{1}[0-9]{4}$";
        if( !Pattern.compile(regex).matcher(numeroPermis).matches()){
            tableauErreurs.add(MSG_ERREUR_NUM_PERMIS) ;
            complet = false ;
            System.out.println(MSG_ERREUR_NUM_PERMIS);
        }
        return Pattern.compile(regex).matcher(numeroPermis).matches() ;
    }
    /**
     * validerNumeroPermisPsy(numeroPermisDuMembre)
     * Cette methode s'assure que le numéro de permis doit être composé de 5 chiffres
     * trait d'union et 2 chiffres (ex : 89765-13)
     *
     * @param numeroPermis
     *
     * @return validerNumeroPermisPsy
     */
    static boolean validerNumeroPermisPsy(String numeroPermis)  {
        String regex = "^[0-9]{5}-[0-9]{2}$";
        if( !Pattern.compile(regex).matcher(numeroPermis).matches()){
            tableauErreurs.add(MSG_ERREUR_NUM_PERMIS) ;
            complet = false ;
            System.out.println(MSG_ERREUR_NUM_PERMIS);
        }
        return Pattern.compile(regex).matcher(numeroPermis).matches() ;
    }

    /**
     * TrouverInitiales(JSONArray informationActivite)
     * Cette methode retourne la premierte lettre du nom et la premiere lettre
     * du prenom du membre en majuscules
     *
     * @param
     *
     *
     */
    static String trouverInitiales(JSONObject fichier ) {
            String initiales = ""+fichier.getString(CLE_NOM).charAt(0) + "" +
                    fichier.getString(CLE_PRENOM).charAt(0);
            return initiales ;
    }


    /**
     * validerNumeroPermisGeo(numeroPermisDuMembre)
     * Cette methode s'assure que e numéro de permis soit  composé de 2 lettres suivies de 4 chiffres.
     * La première lettre du numéro de permis correspond à la première lettre du nom du membre en
     * majuscule. La deuxième lettre du numéro de permis correspond à la première lettre du prénom
     * du membre en majuscule.
     *
     * @param numeroPermis
     *
     * @return validerNumeroPermisGeo
     *
     */
    static boolean validerNumeroPermisGeo(String numeroPermis, JSONObject fichier) {
        String initiales = trouverInitiales(fichier);
        String regex = initiales+"[0-9]{4}$";
        if (!Pattern.compile(regex).matcher(numeroPermis).matches()) {
            tableauErreurs.add(MSG_ERREUR_NUM_PERMIS);
            complet = false;
            System.out.println(MSG_ERREUR_NUM_PERMIS);
        }
        return Pattern.compile(regex).matcher(numeroPermis).matches();
    }

    /**
     * validerNumeroPermisPodiatre(numeroPermisDuMembre)
     * Cette methode s'assure que le numéro de permis se compose uniquement de 5 chiffres (ex: 83453)
     *
     * @param numeroPermis
     *
     * @return validerNumeroPermisPodiatre
     */
    static boolean validerNumeroPermisPodiatre(String numeroPermis)  {
        String regex = "^[0-9]{5}$";
        if( !Pattern.compile(regex).matcher(numeroPermis).matches()){
            tableauErreurs.add(MSG_ERREUR_NUM_PERMIS) ;
            complet = false ;
            System.out.println(MSG_ERREUR_NUM_PERMIS);
        }
        return Pattern.compile(regex).matcher(numeroPermis).matches() ;
    }

    /**
     * validerDescription(description)
     * Cette methode s'assure qu'une description de plus de 20 caractères
     * doit être fournie pour chaque activité
     *
     * @param informationActivite
     * @return validerDescription
     */
    static JSONArray validerDescription(JSONArray informationActivite)  {
        for (int i = 0; i < informationActivite.size(); i++) {
            if(!informationActivite.getJSONObject(i).getString(CLE_CATEGORIE).equals("Ignored")) {
                if (informationActivite.getJSONObject(i).getString(CLE_DESCRIPTION).length() < 20) {
                    tableauErreurs.add("Erreur, Description Invalide pour la categorie "+informationActivite.getJSONObject(i).getString(CLE_CATEGORIE)) ;
                    informationActivite.getJSONObject(i).put(CLE_CATEGORIE,"Ignored") ;
                }
            }
        }
        return informationActivite ;
    }

    /**
     * validation des contraintes propres aux architectes
     *
     * @param informationActivite
     * @param heuresSuppFormation
     * @param cycle
     */
    static void validerArchitecte(JSONArray informationActivite, int heuresSuppFormation , String cycle){
        validerCategories(informationActivite);
        informationActivite = validerDatesActivitesDeclareesCycle(informationActivite,cycle) ;
        informationActivite = validerDescription(informationActivite) ;
        validerHeuresTransferees(heuresSuppFormation) ;
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(informationActivite) ;
        validerHeures(parcoursFichierJson);
        validerHeuresSpecifiqueArchitectes(parcoursFichierJson);
        validerHeuresTotalArchitectes(parcoursFichierJson,heuresSuppFormation , cycle);
    }

    /**
     * validation des contraintes propres aux geologues
     *
     * @param informationActivite
     */
    static void validerGeologue(JSONArray informationActivite){
        validerCategories(informationActivite);
        informationActivite = validerGeologueDatesActivitesDeclarees(informationActivite) ;
        informationActivite = validerDescription(informationActivite) ;
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(informationActivite) ;
        validerHeures(parcoursFichierJson);
        validerHeuresSpecifiqueGeologues(parcoursFichierJson);
        validerHeuresTotalGeologues(parcoursFichierJson);
    }
    /**
     * validation des contraintes propres aux podiatres
     *
     * @param informationActivite
     */
    static void validerPodiatres(JSONArray informationActivite){
        validerCategories(informationActivite);
        informationActivite = validerPodiatreDatesActivitesDeclarees(informationActivite) ;
        informationActivite = validerDescription(informationActivite) ;
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(informationActivite) ;
        validerHeures(parcoursFichierJson);
        validerHeuresSpecifiquePodiatres(parcoursFichierJson);
        validerHeuresTotalPodiatres(parcoursFichierJson);
    }

    /**
     * validation des contraintes propres aux psychologues
     *
     * @param informationActivite
     */
    static void validerPsychologues(JSONArray informationActivite){
        validerCategories(informationActivite);
        informationActivite = validerDatesActivitesDeclareesPourPsychologue(informationActivite) ;
        informationActivite = validerDescription(informationActivite) ;
        ParcoursFichierJson parcoursFichierJson = new ParcoursFichierJson(informationActivite) ;
        validerHeures(parcoursFichierJson);
        validerHeuresSpecifiquePsychologues(parcoursFichierJson);
        validerHeuresTotalPsychologues(parcoursFichierJson);
    }

    /**
     * Verifie l'existence des heures transferees du cycle
     *
     * @param lecteur
     */
    static boolean verifierSiHeuresTransfereesExiste(JSONObject lecteur){
        if(lecteur.has(CLE_HEURES_SUPP)){
            System.out.println("\nLe champs heures transferees du cycle precedent ne s'applique pas pour cet ordre ! \n");
            System.exit(1);
        }
        return true ;
    }

    /**
     * Valide que les heures d'une activite ne depassent pas 10 par jour
     * @param heures
     * @param nomCategorie
     * @return
     */
    static int validerHeuresMinParJour(int heures , String nomCategorie){
        if(heures > 10) {
            if (!tableauErreurs.contains(MSG_ERR_HEURES_MIN_1 + nomCategorie + MSG_ERR_HEURES_MIN_2)) {
                tableauErreurs.add(MSG_ERR_HEURES_MIN_1 + nomCategorie + MSG_ERR_HEURES_MIN_2);
            }
            heures = 10;
        }
        return heures ;
    }

    /**
     * Faire les manipulations liees aux architectes
     * @param lecteur
     */
    static boolean gererArchitectes(JSONObject lecteur){
        boolean valide = validerStructureArchitecte(lecteur) ;
        if(valide) {
            if(validerCycleArchitecte(lecteur.getString(CLE_CYCLE)))
            validerArchitecte(lecteur.getJSONArray(CLE_ACTIVITES), lecteur.getInt(CLE_HEURES_SUPP),lecteur.getString(CLE_CYCLE));
        }
        return valide ;
    }

    /**
     * Faire les manipulations liees aux psychologues
     * @param lecteur
     */
    static void gererPsychologues(JSONObject lecteur){
        verifierSiHeuresTransfereesExiste(lecteur);
       if( validerCyclePsy(lecteur.getString(CLE_CYCLE)) )
        validerPsychologues(lecteur.getJSONArray(CLE_ACTIVITES));
    }

    /**
     * Faire les manipulations liees aux geologues
     * @param lecteur
     */
    static void gererGeologues(JSONObject lecteur){
        verifierSiHeuresTransfereesExiste(lecteur);
        if(validerGeologueCycle(lecteur.getString(CLE_CYCLE)))
        validerGeologue(lecteur.getJSONArray(CLE_ACTIVITES));
    }

    /**
     * Faire les manipulations liees aux podiatres
     * @param lecteur
     */
    static void gererPodiatres(JSONObject lecteur){
        verifierSiHeuresTransfereesExiste(lecteur);
        if(validerPodiatreCycle(lecteur.getString(CLE_CYCLE)))
            validerPodiatres(lecteur.getJSONArray(CLE_ACTIVITES));
    }

    /**
     * Gere la validation des numeros de permis
     *
     * @param fichierJson
     * @return si permis valide ou pas
     */
    static boolean validerNumPermis(JSONObject fichierJson){
        return validerNumeroPermisArchi(fichierJson.getString(CLE_NUM_PERMIS))||
                validerNumeroPermisPsy(fichierJson.getString(CLE_NUM_PERMIS)) ||
                validerNumeroPermisGeo(fichierJson.getString(CLE_NUM_PERMIS), fichierJson) ||
                validerNumeroPermisPodiatre(fichierJson.getString(CLE_NUM_PERMIS)) ;
    }

    /**
     * Fonction centrale de validation
     *
     * @param fichierJson
     */
    public static void validerTout(JSONObject fichierJson) {
        if(validerStructureFichierOrdres(fichierJson)) {
            if (validerNumPermis(fichierJson)) {
                if (fichierJson.getString(CLE_ORDRE).equals(ORDRE_ARCHITECTE)) gererArchitectes(fichierJson);
                else if (fichierJson.getString(CLE_ORDRE).equals(ORDRE_PSY)) gererPsychologues(fichierJson);
                else if (fichierJson.getString(CLE_ORDRE).equals(ORDRE_GEOLOGUE)) gererGeologues(fichierJson);
                else if (fichierJson.getString(CLE_ORDRE).equals(ORDRE_PODIATRE)) gererPodiatres(fichierJson) ;
            }
        }
    }

    /**
     * Permet de valider la structure du fichier selon l'odre architecte
     *
     * @param lecteur
     * @return
     */
    static boolean validerStructureArchitecte(JSONObject lecteur) {
        if(!(lecteur.has(CLE_HEURES_SUPP))){
            tableauErreurs.add("Structure du fichier invalide ! \n") ;
            complet=false;
            System.out.println("Structure du fichier invalide ! \n");
        }
        return lecteur.has(CLE_HEURES_SUPP) ;
    }

    /**
     * Validation des heures(heures positives)
     *
     * @param informationActivite
     */
    public static void validerHeures(ParcoursFichierJson informationActivite) {
        validerHeuresActivites(informationActivite.parcourirCategorieGrpDiscussion(),VALEUR_GRP_DISCUSSION);
        validerHeuresActivites(informationActivite.parcourirCategoriePresentation(),VALEUR_PRESENTATION);
        validerHeuresActivites(informationActivite.parcourirCategorieProjetRecherche(), VALEUR_PROJET_RECHERCHE);
        validerHeuresActivites(informationActivite.parcourirCategorieRedactionPro(), VALEUR_REDACTION_PRO);
        validerHeuresActivites(informationActivite.parcourirCategorieCours(), VALEUR_COURS);
        validerSuite(informationActivite);
    }

    /**
     * Validation des heures(heures positives)
     *
     * @param informationActivite
     */
    static void validerSuite(ParcoursFichierJson informationActivite){
        validerHeuresActivites(informationActivite.parcourirCategorieAtelier(), VALEUR_ATELIER);
        validerHeuresActivites(informationActivite.parcourirCategorieSeminaire(), VALEUR_SEMINAIRE);
        validerHeuresActivites(informationActivite.parcourirCategorieColloque(), VALEUR_COLLOQUE);
        validerHeuresActivites(informationActivite.parcourirCategorieConference(), VALEUR_CONFERENCE);
        validerHeuresActivites(informationActivite.parcourirCategorieLectureDirigee(), VALEUR_LECTURE_DIRIGE);
    }

    /**
     * Validation heures pour ordre architecte
     *
     * @param informationActivite
     */
    static void validerHeuresSpecifiqueArchitectes(ParcoursFichierJson informationActivite){
        validerHeuresMaxDePresentation(informationActivite.parcourirCategoriePresentation());
        validerHeuresGroupeDiscussion(informationActivite.parcourirCategorieGrpDiscussion());
        validerHeuresProjetRecherche(informationActivite.parcourirCategorieProjetRecherche());
        validerHeuresRedactionPro(informationActivite.parcourirCategorieRedactionPro());
    }

    /**
     * Validation heures pour ordre podiatres
     *
     * @param informationActivite
     */
    static boolean validerHeuresSpecifiquePodiatres(ParcoursFichierJson informationActivite){
        return validerPodiatreHeuresCours(informationActivite.parcourirCategorieCours()) &&
        validerPodiatreHeuresGroupeDiscussion(informationActivite.parcourirCategorieGrpDiscussion()) &&
        validerPodiatreHeuresProjetRecherche(informationActivite.parcourirCategorieProjetRecherche()) ;
    }

    /**
     * Validation heures pour ordre geologue
     *
     * @param informationActivite
     */
    static boolean validerHeuresSpecifiqueGeologues(ParcoursFichierJson informationActivite){
        return validerGeologueHeuresGroupeDiscussion(informationActivite.parcourirCategorieGrpDiscussion()) &&
                validerGeologueHeuresCours(informationActivite.parcourirCategorieCours()) &&
                validerGeologueHeuresProjetRecherche(informationActivite.parcourirCategorieProjetRecherche()) ;
    }

    /**
     * Validation heures pour ordre psychologue
     *
     * @param informationActivite
     */
    static void validerHeuresSpecifiquePsychologues(ParcoursFichierJson informationActivite){
        validerHeuresConference(informationActivite.parcourirCategorieConference());
        validerHeuresMinimumCours(informationActivite.parcourirCategorieCours()) ;
    }

    /**
     * valider heures de certaines categories
     *
     * @param informationActivite
     * @param heuresTransferees
     */
    static boolean validerHeuresGrpCategorie(ParcoursFichierJson informationActivite, int heuresTransferees){
        return validerHeuresDugpeDeCteg(informationActivite.parcourirCategorieCours()+
                informationActivite.parcourirCategorieSeminaire()+informationActivite.parcourirCategorieAtelier()+
                informationActivite.parcourirCategorieColloque()+informationActivite.parcourirCategorieConference()+
                informationActivite.parcourirCategorieLectureDirigee()+heuresTransferees);
    }

    /**
     * validr totales des heures prdre architecte
     *
     * @param informationActivite
     * @param heuresTransferees
     * @param cycle
     */
    static boolean validerHeuresTotalArchitectes(ParcoursFichierJson informationActivite, int heuresTransferees , String cycle){
        return validerHeuresGrpCategorie(informationActivite,heuresTransferees) &&
                validerHeuresDeFormation(informationActivite.parcourirCategorieGrpDiscussion()+
                        informationActivite.parcourirCategoriePresentation()+
                        informationActivite.parcourirCategorieProjetRecherche()+
                        informationActivite.parcourirCategorieRedactionPro()+ informationActivite.parcourirCategorieCours()+
                        informationActivite.parcourirCategorieAtelier()+ informationActivite.parcourirCategorieSeminaire()+
                        informationActivite.parcourirCategorieColloque()+ informationActivite.parcourirCategorieConference()+
                        informationActivite.parcourirCategorieLectureDirigee()+heuresTransferees,cycle);
    }

    /**
     * valider heures totales geologues
     *
     * @param informationActivite
     */
    static boolean validerHeuresTotalGeologues(ParcoursFichierJson informationActivite){
        return validerHeuresGrpCategorie(informationActivite,0) &&
                validerGeologueHeuresDeFormation(informationActivite.parcourirCategorieGrpDiscussion()+
                        informationActivite.parcourirCategoriePresentation()+
                        informationActivite.parcourirCategorieProjetRecherche()+
                        informationActivite.parcourirCategorieRedactionPro()+ informationActivite.parcourirCategorieCours()+
                        informationActivite.parcourirCategorieAtelier()+ informationActivite.parcourirCategorieSeminaire()+
                        informationActivite.parcourirCategorieColloque()+ informationActivite.parcourirCategorieConference()+
                        informationActivite.parcourirCategorieLectureDirigee());
    }

    /**
     * valider heures totales podiatres
     *
     * @param informationActivite
     */
    static boolean validerHeuresTotalPodiatres(ParcoursFichierJson informationActivite){
        return validerHeuresGrpCategorie(informationActivite,0) &&
                validerPodiatreHeuresDeFormation(informationActivite.parcourirCategorieGrpDiscussion()+
                        informationActivite.parcourirCategoriePresentation()+
                        informationActivite.parcourirCategorieProjetRecherche()+
                        informationActivite.parcourirCategorieRedactionPro()+ informationActivite.parcourirCategorieCours()+
                        informationActivite.parcourirCategorieAtelier()+ informationActivite.parcourirCategorieSeminaire()+
                        informationActivite.parcourirCategorieColloque()+ informationActivite.parcourirCategorieConference()+
                        informationActivite.parcourirCategorieLectureDirigee());
    }

    /**
     * valider heures totales psychologues
     *
     * @param informationActivite
     */
    static boolean validerHeuresTotalPsychologues(ParcoursFichierJson informationActivite){
        return validerHeuresGrpCategorie(informationActivite,0) &&
                validerHeuresDeFormationPsychologue(informationActivite.parcourirCategorieGrpDiscussion()+
                        informationActivite.parcourirCategoriePresentation()+
                        informationActivite.parcourirCategorieProjetRecherche()+
                        informationActivite.parcourirCategorieRedactionPro()+ informationActivite.parcourirCategorieCours()+
                        informationActivite.parcourirCategorieAtelier()+ informationActivite.parcourirCategorieSeminaire()+
                        informationActivite.parcourirCategorieColloque()+ informationActivite.parcourirCategorieConference()+
                        informationActivite.parcourirCategorieLectureDirigee());
    }

    /**
     * valider la structure des activites
     *
     * @param informationActivite
     * @return structure validee ou non
     */
    static boolean validerStructureCategoriesActivites(JSONArray informationActivite){
        boolean bool = true ;
        for (int i = 0; i < informationActivite.size(); i++) {
            if(!(informationActivite.getJSONObject(i).has(CLE_DESCRIPTION) && informationActivite.getJSONObject(i).has(CLE_DATE)&&
                    informationActivite.getJSONObject(i).has(CLE_CATEGORIE) && informationActivite.getJSONObject(i).has(CLE_TOTAL_HEURES))){
                bool = false ;
            }
        }
        return bool ;
    }

    /**
     * valider la structure du fichier
     *
     * @param objetJson
     * @return structure validee ou non
     */
    static boolean validerStructureFichierOrdres(JSONObject objetJson){
        boolean bool = true ;
        if(!(objetJson.has(CLE_ORDRE) && objetJson.has(CLE_NUM_PERMIS ) && objetJson.has(CLE_PRENOM) &&
                objetJson.has(CLE_ACTIVITES) && objetJson.has(CLE_CYCLE) && objetJson.has(CLE_NOM) &&
                validerStructureCategoriesActivites(objetJson.getJSONArray(CLE_ACTIVITES)))){
            bool = false ; complet = false ; tableauErreurs.add("Structure du fichier invalide ! \n") ;
            System.out.println("Structure du fichier invalide ! \n") ;
        }
        return bool ;
    }

}
