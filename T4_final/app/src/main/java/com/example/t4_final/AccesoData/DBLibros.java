package com.example.t4_final.AccesoData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBLibros extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOM = "libros";
    private static final String TABLA_LIBROS = "CREATE TABLE Libros(Titulo TEXT, Resumen TEXT, Autor TEXT, Fecha TEXT, Tienda1 TEXT, Tienda2 TEXT, Tienda3 TEXT)";

    public DBLibros(@Nullable Context context) {
        super(context, DATABASE_NOM, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_LIBROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL(TABLA_LIBROS);
    }
}
