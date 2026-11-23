package com.example.practicaexamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {
    private static final String name = "Fonda";
    private static final int version = 1;
    public BaseDeDatos(@Nullable Context context) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE fondita " +
                "( nombre TEXT NOT NULL, " +
                " precio NUMBER(3) NOT NULL," +
                "descripcion TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
