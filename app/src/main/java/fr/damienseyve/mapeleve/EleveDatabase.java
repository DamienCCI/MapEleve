package fr.damienseyve.mapeleve;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EleveDatabase extends SQLiteOpenHelper {

    public static String nomTableEleve = "Eleve";
    public static String id = "idEle";
    public static String prenomEleve = "prenomEle";
    public static String nomEleve = "nomEle";
    public static String adresseEleve = "adresseEle";
    public static String cpEleve = "cpEleve";
    public static String villeEleve = "villeEle";
    public static String telEleve = "telEle";

    private static final String DATABASE_CREATE = "CREATE TABLE " + nomTableEleve + " ("
            + id + " INTEGER PRIMARY KEY , "
            + prenomEleve + " TEXT, "
            + nomEleve + " TEXT, "
            + adresseEleve + " TEXT, "
            + cpEleve + " TEXT, "
            + villeEleve + " TEXT, "
            + telEleve + " TEXT);";

    public EleveDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on crée la table table_contacts dans la BDD
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on supprime la table table_contacts de la BDD et on recrée la BDD
        db.execSQL("DROP TABLE " + nomTableEleve + ";");
        onCreate(db);
    }

}
