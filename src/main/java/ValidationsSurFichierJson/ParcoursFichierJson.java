package ValidationsSurFichierJson;

import net.sf.json.JSONArray;

import static Traitement.Constantes.*;
import static ValidationsSurFichierJson.ValidationContraintesFormation.validerHeuresMinParJour;

public class ParcoursFichierJson {

    //Variable d'instance
    private JSONArray informationActivite;

    /**
     * Constructeur de classe
     *
     * @param informationActivite
     */
    public ParcoursFichierJson(JSONArray informationActivite){
        this.informationActivite = informationActivite ;
    }

    /**
     * parcoursActivites(cle,valeur)
     *
     * Permet de trouver les heures totales d'une categorie d'activite precise
     *
     * @param cle
     * @param valeur
     * @return les heures totales de cette activite
     */
    private int parcourirActivites(String cle, String valeur){
        int activite = 0 ;
        for (int i = 0; i < informationActivite.size() ; i++) {
            if (valeur.equals(informationActivite.getJSONObject(i).getString(cle))) {
                int heureDeActivite = informationActivite.getJSONObject(i).getInt(CLE_TOTAL_HEURES) ;
                heureDeActivite = validerHeuresMinParJour(heureDeActivite,valeur);
                activite = activite + heureDeActivite;}
        }
        return activite ;
    }

    /**
     * parcoursCategoriePresentation()
     *
     * Calcule les heures totales de la categorie presentation
     *
     * @return nombre heures totales de la categorie presentation
     */
    public int parcourirCategoriePresentation() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_PRESENTATION) ;
    }

    /**
     * parcoursCategorieGrpDiscussion()
     *
     * Calcule les heures totales de la categorie groupe de discussion
     *
     * @return nombre heures totales de la categorie groupe de discussion
     */
    public int parcourirCategorieGrpDiscussion() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_GRP_DISCUSSION) ;
    }

    /**
     * parcoursCategorieProjetRecherche()
     *
     * Calcule les heures totales de la categorie projet de recherche
     *
     * @return nombre heures totales de la categorie projet de recherche
     */
    public int parcourirCategorieProjetRecherche() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_PROJET_RECHERCHE) ;
    }

    /**
     * parcoursCategorieRedactionPro()
     *
     * Calcule les heures totales de la categorie redaction professionnelle
     *
     * @return nombre heures totales de la categorie redaction professionnelle
     */
    public int parcourirCategorieRedactionPro() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_REDACTION_PRO) ;
    }

    /**
     * parcoursCategorieCours()
     *
     * Calcule les heures totales de la categorie cours
     *
     * @return nombre heures totales de la categorie cours
     */
    public int parcourirCategorieCours() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_COURS) ;
    }

    /**
     * parcoursCategorieAtelier()
     *
     * Calcule les heures totales de la categorie atelier
     *
     * @return nombre heures totales de la categorie atelier
     */
    public int parcourirCategorieAtelier() {
        return parcourirActivites(CLE_CATEGORIE, VALEUR_ATELIER) ;
    }

    /**
     * parcoursCategorieSeminaire()
     *
     * Calcule les heures totales de la categorie seminaire
     *
     * @return nombre heures totales de la categorie seminaire
     */
    public int parcourirCategorieSeminaire() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_SEMINAIRE) ;
    }

    /**
     * parcoursCategorieColloque()
     *
     * Calcule les heures totales de la categorie colloque
     *
     * @return nombre heures totales de la categorie colloque
     */
    public int parcourirCategorieColloque() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_COLLOQUE) ;
    }

    /**
     * parcoursCategorieConference()
     *
     * Calcule les heures totales de la categorie conference
     *
     * @return nombre heures totales de la categorie conference
     */
    public int parcourirCategorieConference() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_CONFERENCE) ;
    }

    /**
     * parcoursCategorieLectureDirigee()
     *
     * Calcule les heures totales de la categorie lecture dirigee
     *
     * @return nombre heures totales de la categorie lecture dirigee
     */
    public int parcourirCategorieLectureDirigee() {
        return parcourirActivites(CLE_CATEGORIE,VALEUR_LECTURE_DIRIGE) ;
    }

}
