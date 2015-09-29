package fr.damienseyve.mapeleve;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EleveManip {

    private static final int versionBdd = 1;
    private static final String nomBdd = "eleve.db";

    private static final String nomTableEleve = "Eleve";

    private static final String colId = "idEle";
    private static final int numColId = 0;

    private static final String colPrenom = "prenomEle";
    private static final int numColPrenom = 1;

    private static final String colNom = "nomEle";
    private static final int numColNom = 2;

    private static final String colAdresse = "adresseEle";
    private static final int numColAdresse = 3;

    private static final String colCp = "cpEleve";
    private static final int numColCp = 4;

    private static final String colVille = "villeEle";
    private static final int numColVille = 5;


    private static final String colTel = "telEle";
    private static final int numColTel = 6;

    private SQLiteDatabase baseDeDonnee;

    private EleveDatabase maBaseSQLite;

    public EleveManip(Context context) {
        maBaseSQLite = new EleveDatabase(context, nomBdd, null, versionBdd);
    }

    /**
     * Ouvre la BDD en écriture
     */
    public void open() {
        baseDeDonnee = maBaseSQLite.getWritableDatabase();
    }

    /**
     * Ferme l'accès à la BDD
     */
    public void close() {
        baseDeDonnee.close();
    }

    public SQLiteDatabase getBDD() {
        return baseDeDonnee;
    }

    /**
     * Insère un contact en base de données
     *
     *            le contact à insérer
     * @return l'identifiant de la ligne insérée
     */
    public long insertEleve(Eleve eleve) {
        ContentValues values = new ContentValues();

        // On insère les valeurs dans le ContentValues : on n'ajoute pas
        // l'identifiant car il est créé automatiquement
        values.put(colPrenom, eleve.getPrenomEleve());
        values.put(colNom, eleve.getNomEleve());
        values.put(colAdresse, eleve.getAdresseEleve());
        values.put(colCp, eleve.getCpEleve());
        values.put(colVille, eleve.getVilleEleve());
        values.put(colTel, eleve.getTelEleve());

        return baseDeDonnee.insert(nomTableEleve, null, values);
    }

    /**
     * Met à jour le contact en base de données
     *
     * @param id
     *            l'identifiant du contact à modifier
     *
     *            le nouveau contact à associer à l'identifiant
     * @return le nombre de lignes modifiées
     */
    public int updateContact(int id, Eleve eleve) {
        ContentValues values = new ContentValues();
        values.put(colPrenom, eleve.getPrenomEleve());
        values.put(colNom, eleve.getNomEleve());
        values.put(colAdresse, eleve.getAdresseEleve());
        values.put(colCp, eleve.getCpEleve());
        values.put(colVille, eleve.getVilleEleve());
        values.put(colTel, eleve.getTelEleve());
        return baseDeDonnee.update(nomTableEleve, values, colId + " = " + id, null);
    }

    /**
     * Supprime un contact de la BDD (celui dont l'identifiant est passé en
     * paramètres)
     *
     * @param id
     *            l'identifiant du contact à supprimer
     * @return le nombre de contacts supprimés
     */
    public int removeEleveWithID(int id) {
        return baseDeDonnee.delete(nomTableEleve, colId + " = " + id, null);
    }

    /**
     * Retourne le premier contact dont le numéro de téléphone correspond à
     * celui en paramètre
     *
     * @param numeroTelephone
     *            le numéro de téléphone
     * @return le contact récupéré depuis la base de données
     */
    public Eleve getFirstContactWithNumeroTelephone(String numeroTelephone) {
        Cursor c = baseDeDonnee.query(nomTableEleve, new String[] { colId, colPrenom,
                colNom, colAdresse, colCp, colVille, colTel }, colTel + " LIKE \""+ numeroTelephone + "\"", null, null, null, null);
        c.moveToFirst();
        Eleve e = cursorToEleve(c);
        c.close();
        return e;
    }

    /**
     * Convertit le cursor en contact
     *
     * @param c
     *            le cursor à convertir
     * @return le Contact créé à partir du Cursor
     */
    private Eleve cursorToEleve(Cursor c) {
        // si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        // Sinon on se place sur le premier élément

        Eleve eleve = new Eleve(0, "", "", "", "", "", "");
        eleve.setId(c.getInt(numColId));
        eleve.setPrenomEleve(c.getString(numColPrenom));
        eleve.setNomEleve(c.getString(numColNom));
        eleve.setAdresseEleve(c.getString(numColAdresse));
        eleve.setCpEleve(c.getString(numColCp));
        eleve.setVilleEleve(c.getString(numColVille));
        eleve.setTelEleve(c.getString(numColTel));

        //c.close();

        return eleve;
    }

    public ArrayList<Eleve> getAllEleves (){
        ArrayList<Eleve> eleves = new ArrayList<>();

        Cursor c = baseDeDonnee.query(nomTableEleve, new String[] { colId, colPrenom,
                colNom, colAdresse, colCp, colVille, colTel }, null , null, null, null, null);
        if(c.getCount()>0) {
            c.moveToFirst();
            eleves.add(cursorToEleve(c));
            while (c.moveToNext()) {
                eleves.add(cursorToEleve(c));
            }
        }
        return eleves;
    }

    public int getLastID(){
        Cursor c = baseDeDonnee.query(nomTableEleve, new String[] { colId, colPrenom,
                colNom, colAdresse, colCp, colVille, colTel }, null , null, null, null, null);
        if(c.getCount()>0) {
            c.moveToLast();
            Eleve eleve = cursorToEleve(c);
            return eleve.getId()+1;
        }else{
            return 0;
        }
    }
}