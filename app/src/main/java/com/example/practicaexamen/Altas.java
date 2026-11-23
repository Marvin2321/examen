package com.example.practicaexamen;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Altas extends AppCompatActivity {
    EditText nombreAl, precioAl, descripcionAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_altas);
        nombreAl = findViewById(R.id.nombreal);
        precioAl = findViewById(R.id.precioal);
        descripcionAl = findViewById(R.id.descripcional);
    }

    public void darDeAlta(View view) {
        String nombre = nombreAl.getText().toString().trim();
        String precio = precioAl.getText().toString().trim();
        String descripcion = descripcionAl.getText().toString().trim();

        if (nombre.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Nombre y precio son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            BaseDeDatos obj = new BaseDeDatos(this);
            SQLiteDatabase db = obj.getWritableDatabase();
            ContentValues fila = new ContentValues();
            fila.put("nombre", nombre);
            fila.put("precio", precio);
            fila.put("descripcion", descripcion);
            db.insert("fondita", null, fila);
            db.close();
            obj.close();

            nombreAl.setText("");
            precioAl.setText("");
            descripcionAl.setText("");
            Toast.makeText(this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void regresarSelector(View view) {
        finish();
    }
}