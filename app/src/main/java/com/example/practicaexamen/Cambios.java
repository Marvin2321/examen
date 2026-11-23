package com.example.practicaexamen;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Cambios extends AppCompatActivity {
    EditText nombreCa, precioCa, descripcionCa;
    Comida[] lista = new Comida[0];
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cambios);

        nombreCa = findViewById(R.id.nombreca);
        precioCa = findViewById(R.id.precioca);
        descripcionCa = findViewById(R.id.descripcionca);

        cargarLista();
        mostrar();
    }

    private void cargarLista() {
        BaseDeDatos obj = new BaseDeDatos(this);
        SQLiteDatabase db = obj.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT rowid, nombre, precio, descripcion FROM fondita", null);
        lista = new Comida[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            lista[i++] = new Comida(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        }
        c.close();
        db.close();
        obj.close();
    }

    private void mostrar() {
        if (lista.length == 0) {
            nombreCa.setText("");
            precioCa.setText("");
            descripcionCa.setText("");
            Toast.makeText(this, "No hay comidas aún", Toast.LENGTH_SHORT).show();
        } else {
            nombreCa.setText(lista[contador].nombre);
            precioCa.setText(lista[contador].precio);
            descripcionCa.setText(lista[contador].descripcion);
        }
    }

    public void siguiente(View view) {
        if (contador < lista.length - 1) {
            contador++;
            mostrar();
        }
    }

    public void anterior(View view) {
        if (contador > 0) {
            contador--;
            mostrar();
        }
    }

    public void guardarCambios(View view) {
        if (lista.length == 0) {
            Toast.makeText(this, "No hay nada que modificar", Toast.LENGTH_SHORT).show();
            return;
        }
        String nombre = nombreCa.getText().toString().trim();
        String precio = precioCa.getText().toString().trim();
        String descripcion = descripcionCa.getText().toString().trim();

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
            db.update("fondita", fila, "rowid=?", new String[]{String.valueOf(lista[contador].id)});
            db.close();
            obj.close();

            cargarLista();
            mostrar();
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void regresarCambios(View view) {
        finish();
    }
}