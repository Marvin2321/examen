package com.example.practicaexamen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Eliminar extends AppCompatActivity {
    EditText nombreBaj, precioBaj, descripcionBaj;
    Comida[] lista = new Comida[0];
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);

        nombreBaj = findViewById(R.id.nombreba);
        precioBaj = findViewById(R.id.precioba);
        descripcionBaj = findViewById(R.id.descripcionba);

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
            nombreBaj.setText("");
            precioBaj.setText("");
            descripcionBaj.setText("");
            Toast.makeText(this, "No hay comidas aún", Toast.LENGTH_SHORT).show();
        } else {
            nombreBaj.setText(lista[contador].nombre);
            precioBaj.setText(lista[contador].precio);
            descripcionBaj.setText(lista[contador].descripcion);
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

    public void darDeBaja(View view) {
        if (lista.length == 0) {
            Toast.makeText(this, "No hay nada que eliminar", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            BaseDeDatos obj = new BaseDeDatos(this);
            SQLiteDatabase db = obj.getWritableDatabase();
            db.delete("fondita", "rowid=?", new String[]{String.valueOf(lista[contador].id)});
            db.close();
            obj.close();

            cargarLista();
            if (contador >= lista.length) {
                contador = Math.max(0, lista.length - 1);
            }
            mostrar();
            Toast.makeText(this, "Eliminado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void regresarEliminar(View view) {
        finish();
    }
}