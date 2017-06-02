package com.example.carlos.dbequipos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EquiposSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION_BASE_DE_DATOS = 1;
    private static final String NOMBRE_BASE_DE_DATOS = "equipos.db";
    private String sql = "CREATE TABLE Equipos(foto text, nombre text, liga text, dt text, mj text, estadio text, apodo text)";


    public EquiposSQLiteOpenHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Equipos");
        db.execSQL(sql);
    }
}
