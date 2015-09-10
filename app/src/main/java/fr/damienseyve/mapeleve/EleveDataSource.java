package fr.damienseyve.mapeleve;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EleveDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public EleveDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public Eleve createEleve (String Eleve) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, Eleve);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Eleve newEleve = cursorToEleve(cursor);
        cursor.close();
        return newEleve;
    }

    private Eleve cursorToEleve(Cursor cursor) {
        //http://vogella.developpez.com/tutoriels/android/utilisation-base-donnees-sqlite/
        Eleve eleve = new Eleve(null);
        eleve.setId(cursor.getLong(0));
        eleve.setPrenomEleve(cursor.getString(1));
        eleve.setNomEleve(cursor.getString(2));
        eleve.setAdresseEleve(cursor.getString(3));
        eleve.setCpEleve(cursor.getString(4));
        eleve.setVilleEleve(cursor.getString(5));
        eleve.setTelEleve(cursor.getString(6));
        return eleve;
    }


}
