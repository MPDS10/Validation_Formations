package GestionFichierJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.collections.map.UnmodifiableOrderedMap;

import java.util.ArrayList;

import static Traitement.Constantes.*;

/**
 * GestionFichierJson.LectureEcritureFichierJson.java
 *
 * Cette classe permet la manipulation du fichier JSON passe en argument.
 * Elle permet entre autre de lire un fichier JSON, ou d'en generer un.
 *
 * @author Ekoue Andy Scott Messan
 * @author Sow Mame Penda Dieng
 * @author Gueye Fatou
 * @author Gicemuzi Armand-Donnel
 */
public class LectureEcritureFichierJson {

    //Declaration attributs d'instance
    private String cheminFichierEntreeJson;
    private static String fichierDeSortieJson ;
    private String jsonEnString ;
    private JSONObject objetJson = new JSONObject();

    /*
    Ces variables seront utilisees pour facilter la manipulation du fichier Json
    Dans les autres classes
     */
    private String numeroPermisDuMembre = "";
    private String cycleFormation = "";
    private int heuresSuppFormation = 0 ;
    private JSONArray informationActivite = new JSONArray();
    private String ordre =  "" ;
    private String nom = "";
    private String prenom = "";
    private int sexe = 0;

    /**
     * Constructeur de la classe
     *
     * @param cheminFichierEntreeJson
     * @param fichierDeSortieJson
     */
    public LectureEcritureFichierJson(String cheminFichierEntreeJson, String fichierDeSortieJson) {
        this.cheminFichierEntreeJson = cheminFichierEntreeJson ;
        this.fichierDeSortieJson = fichierDeSortieJson ;
        this.objetJson = null ;
    }

    /**
     * lireFichierJson()
     *
     * Permet de lire le fichier Json
     *
     * @return Vrai si le fichier est lu, faux sinon
     */
    public boolean lireFichierJson(){
        try{
            jsonEnString = FileHash.readFileToString(cheminFichierEntreeJson) ;
            chargerInformationFichier();
        }catch(Exception e){
            System.out.println("\nErreur de lecture du fichier, le fichier est vide/ n'existe pas ! \n"+
                    "Veuillez reessayer avec un chemin de fichier valide.");
        }
        return (objetJson == null ) ? false : true;
    }


    /**
     * chargerInformationFichier()
     *
     * Permet de de charger les informations fichier JSON dans des variables
     */
    void chargerInformationFichier(){
        objetJson = (JSONObject) JSONSerializer.toJSON(jsonEnString) ;
        nom = objetJson.getString(CLE_NOM);
        prenom = objetJson.getString(CLE_PRENOM);
        sexe = objetJson.getInt(CLE_SEXE);
        numeroPermisDuMembre = objetJson.getString(CLE_NUM_PERMIS) ;
        cycleFormation =  objetJson.getString(CLE_CYCLE) ;
        heuresSuppFormation = objetJson.has(CLE_HEURES_SUPP) ? objetJson.getInt(CLE_HEURES_SUPP) : 0 ;
        ordre = objetJson.getString(CLE_ORDRE) ;
        informationActivite = (JSONArray) objetJson.getJSONArray(CLE_ACTIVITES);
    }

    /**
     * @return l'objet correspondant au fichier
     */
    public JSONObject getObjetJson() {return objetJson;}

    /**
     * Ajoute les resultats des validations daans le fichier Json
     *
     * @param complet
     * @param tabErreur
     * @return un objet json
     */
    static JSONObject ajouterInformationsRetour(boolean complet, ArrayList<String> tabErreur){
        JSONObject objetJsonSortie = new JSONObject() ;
        objetJsonSortie.put("complet",complet);
        objetJsonSortie.put("erreurs", tabErreur);
        return objetJsonSortie ;
    }

    /**
     * Ecrit dams le fichier de sortie
     *
     * @param complet
     * @param tabErreur
     */
    public static Boolean ecrireDansFichierJson(boolean complet, ArrayList<String> tabErreur) {
        boolean valide = false;
        JSONObject objetJsonSortie = ajouterInformationsRetour(complet,tabErreur) ;
        try {
            FileHash.writeStringInFile(fichierDeSortieJson, objetJsonSortie.toString(3));
            valide = true;
        }catch (Exception e) {
            System.out.println(MSG_ERREUR_ECRITURE);
        }
        return valide;
    }

}

