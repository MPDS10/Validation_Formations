package Traitement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Traitement.Constantes.java
 *
 * Cette classe contient toutes les constantes utilisées dans le projet
 *
 * @author Ekoue Andy Scott Messan
 * @author Sow Mame Penda Dieng
 * @author Gueye Fatou
 * @author Gicemuzi Armand-Donnel
 */
public class Constantes {
    //declaration des constantes
    public static final int MIN_HEURES_SUPP = 0;
    public static final int MAX_HEURES_SUPP = 7;
    public static final int MINIMUM_HEURES_DE_FORMATION = 40;
    public static final int MINIMUM_HEURES_DES_CATEGORIES = 17;
    public static final int NBR_HEURES_MAX_DE_PRESENTATION = 23;
    public static final int LIMITE_MIN_HEURES_ACTIVITES= 1;
    public static final String MESSAGE_ERREUR_HEURE_ACTIVITE= "Erreur, l'heure est invalide pour la categorie ";
    public static final String CYCLE_SUPPORTE = "2021-2023" ;
    public static final String MSG_ERREUR_CYCLE_FORMATION = "Erreur, le cyle n'est pas supporté" ;
    public static final String MSG_ERREUR_HEURE_DE_SURPLUS_NEGATIF="Erreur, le cycle precedent est negatif";
    public static final String MSG_ERREUR_HEURE_DE_SURLUS_SUPERIEUR_A_7 = "Erreur, les heures supplementaires du cycle precedent sont superieures à 7";
    public static final String MSG_ERREUR_MIN_HEURE_DE_FORMATION= "Erreur, la formation a durer moins de 40 heures";
    public static final String NBR_HEURES_MIN_DU_GPE_DE_CATEGORIE = "Erreur, les heures declarées dans le groupe de catégories est inferieur à 17";
    public static final String MSG_ERREUR_HEURE_CYCLE_PSYCHOLOGUE = "Erreur, la formation a durer moins de 90 heures";
    public static final String MESSAGE_ERREUR_HEURES_COURS = "Erreur, les heures declarées dans le groupe de cours sont inferieur à 25";


    public static final int NB_MAX_HEURES_GROUPE_DISCUSSION = 17 ;
    public static final int NB_MAX_HEURES_CONFERENCE = 15;

    public static final int NB_MAX_HEURES_PROJET_RECHERCHE = 23 ;
    public static final int NB_MAX_HEURES_REDACTION_PRO = 17 ;
    public static final int NBR_HEURES_CYCLE_FORMATION_PSYCHOLOGUE = 90;
    public static final int NBR_HEURES_MIN_DE_COURS = 25;


    public static final String MSG_ERR_DATE_ACTIVITE_1 = "Erreur, la categorie " ;
    public static final String MSG_ERR_DATE_ACTIVITE_2 = " n'a pas ete completée durant la " +
            "bonne periode.";
    public static final String MSG_ERR_DATE = "Erreur, format de date invalide pour la categorie " ;
    public static final String MSG_ERR_CATEGORIES_1 = "Erreur, la categorie ";
    public static final String MSG_ERR_CATEGORIES_2 = " n'est pas reconnue ";
    public static final List<String> categories = Arrays.asList("cours", "atelier", "séminaire", "colloque",
            "conférence", "lecture dirigée", "présentation", "groupe de discussion",
            "projet de recherche", "rédaction professionnelle");
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final LocalDate DATE_MIN_ARCHITECTE_C1 = LocalDate.parse("2021-04-01", FORMATTER);
    public static final LocalDate DATE_MAX_ARCHITECTE_C1 = LocalDate.parse("2023-04-01", FORMATTER);
    public static final LocalDate DATE_MIN_ARCHITECTE_C2 = LocalDate.parse("2018-04-01", FORMATTER);
    public static final LocalDate DATE_MAX_ARCHITECTE_C2 = LocalDate.parse("2020-04-01", FORMATTER);
    public static final LocalDate DATE_MIN_ARCHITECTE_C3 = LocalDate.parse("2016-04-01", FORMATTER);
    public static final LocalDate DATE_MAX_ARCHITECTE_C3 = LocalDate.parse("2018-07-01", FORMATTER);
    public static final LocalDate DATE_MIN_PSYCHOLOGUE = LocalDate.parse("2018-01-01", FORMATTER);
    public static final LocalDate DATE_MAX_PSYCHOLOGUE = LocalDate.parse("2023-01-01", FORMATTER);

    public static final String CLE_CATEGORIE = "categorie" ;
    public static final String CLE_DATE = "date" ;
    //Declaration constantes
    public static final String MSG_ERREUR_FICHIER = "\nErreur de lecture du fichier, le fichier est vide/ n'existe pas ! \n"+
            "Veuillez reessayer avec un chemin de fichier valide.";
    public static final String CLE_NOM = "nom" ;
    public static final String CLE_PRENOM = "prenom" ;
    public static final String CLE_NUM_PERMIS = "numero_de_permis" ;
    public static final String CLE_CYCLE = "cycle" ;
    public static final String CLE_HEURES_SUPP = "heures_transferees_du_cycle_precedent" ;
    public static final String CLE_ACTIVITES = "activites" ;
    public static final String CLE_TOTAL_HEURES = "heures";
    public static final String VALEUR_PRESENTATION = "présentation" ;
    public static final String VALEUR_GRP_DISCUSSION = "groupe de discussion" ;
    public static final String VALEUR_PROJET_RECHERCHE = "projet de recherche" ;
    public static final String VALEUR_REDACTION_PRO = "rédaction professionnelle" ;
    public static final String VALEUR_COURS = "cours" ;
    public static final String VALEUR_ATELIER = "atelier" ;
    public static final String VALEUR_SEMINAIRE = "séminaire" ;
    public static final String VALEUR_COLLOQUE = "colloque" ;
    public static final String VALEUR_CONFERENCE = "conférence" ;
    public static final String VALEUR_LECTURE_DIRIGE = "lecture dirigée" ;
    public static final String MSG_ERREUR_ECRITURE = "\nErreur d'ecriture dans fichier JSON\n" ;
    public static final String ORDRE_ARCHITECTE = "architectes" ;
    public static final String ORDRE_GEOLOGUE = "geologues" ;
    public static final String ORDRE_PSY = "psychologues";
    public static final String CYCLE_SUPPORTE_PSY = "2018-2023" ;
    public static final String CYCLE_SUPPORTE_ARCHITECTE_1 = "2018-2020" ;
    public static final String CYCLE_SUPPORTE_ARCHITECTE_2 = "2016-2018" ;
    public static final String CLE_ORDRE = "ordre";
    /*
     validation ordre Géologue
     */
    public static final String GEO_CYCLE_SUPPORTE = "2019-2022" ;
    public static final int GEO_MINIMUM_HEURES_DE_FORMATION = 55;
    public static final LocalDate GEO_DATE_MIN = LocalDate.parse("2019-06-01", FORMATTER);
    public static final LocalDate GEO_DATE_MAX = LocalDate.parse("2022-06-01", FORMATTER);
    public static final int GEO_MIN_HEURES_COURS= 22 ;
    public static final int GEO_MIN_HEURES_PROJET_RECHERCHE = 3 ;
    public static final int GEO_MIN_HEURES_GROUPE_DISCUSSION = 1 ;
    public static final String GEO_MSG_ERREUR_MIN_HEURES_GROUPE_DISCUSSION = "Erreur, les heures declarées dans le groupe de discussion est inferieur à 1";
    public static final String GEO_MSG_ERREUR_MIN_HEURES_COURS = "Erreur, les heures declarées dans cours est inferieur à 22";
    public static final String GEO_MSG_ERREUR_MIN_HEURES_PROJET_RECHERCHE = "Erreur, les heures declarées dans le projet de recherch est inferieur à 3";

    public static final String MSG_ERREUR_NUM_PERMIS = "Erreur, numero de permis invalide ";
    public static final String CLE_DESCRIPTION = "description" ;
    public static final String MSG_ERREUR_MIN_HEURE_DE_FORMATION_GEO = "Erreur, la formation a durer moins de 55 heures";
    public static final LocalDate DATE_AN_BISS_1 = LocalDate.parse("2020-02-29",FORMATTER);

    public static final int MINIMUM_HEURES_DE_FORMATION_2 = 42;
    public static final String MSG_ERREUR_MIN_HEURE_DE_FORMATION_2 = "Erreur, la formation a durer moins de 42 heures";
    public static String MSG_ERR_HEURES_MIN_1 = "Nombre d'heures de l'activite " ;
    public static String MSG_ERR_HEURES_MIN_2 = " superieure a 10";
    /*
     validation ordre Podiatre
     */
    public static final String POD_CYCLE_SUPPORTE = "2019-2022" ;
    public static final int POD_MINIMUM_HEURES_DE_FORMATION = 60;
    public static final LocalDate POD_DATE_MIN = LocalDate.parse("2019-06-01", FORMATTER);
    public static final LocalDate POD_DATE_MAX = LocalDate.parse("2022-06-01", FORMATTER);
    public static final int POD_MIN_HEURES_COURS= 22 ;
    public static final int POD_MIN_HEURES_PROJET_RECHERCHE = 3 ;
    public static final int POD_MIN_HEURES_GROUPE_DISCUSSION = 1 ;
    public static final String POD_MSG_ERREUR_MIN_HEURES_GROUPE_DISCUSSION = "Erreur, les heures declarées dans le groupe de discussion est inferieur à 1";
    public static final String POD_MSG_ERREUR_MIN_HEURES_COURS = "Erreur, les heures declarées dans cours est inferieur à 22";
    public static final String POD_MSG_ERREUR_MIN_HEURES_PROJET_RECHERCHE = "Erreur, les heures declarées dans le projet de recherche est inferieur à 3";
    public static final String MSG_ERREUR_MIN_HEURE_DE_FORMATION_POD = "Erreur, la formation a durer moins de 60 heures";
    public static final String ORDRE_PODIATRE = "podiatres" ;
    public static String MSG_ERREUR_SEXE = "Erreur le sexe est invalide";
    public static final int SEXE_INCONNU = 0;
    public static final int SEXE_FEMININ = 2;

    public static final String CLE_SEXE= "sexe";
}


