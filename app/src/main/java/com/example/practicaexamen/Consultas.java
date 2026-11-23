package com.example.practicaexamen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Consultas extends AppCompatActivity {
    TextView registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultas);
        registros = findViewById(R.id.registros);

        try {
            BaseDeDatos obj = new BaseDeDatos(this);
            SQLiteDatabase db = obj.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT nombre, precio, descripcion FROM fondita", null);

            StringBuilder sb = new StringBuilder();
            while (c.moveToNext()) {
                sb.append(c.getString(0))
                        .append(" -- $").append(c.getString(1))
                        .append(" -- ").append(c.getString(2))
                        .append("\n\n");
            }
            c.close();
            db.close();
            obj.close();

            if (sb.length() == 0) {
                registros.setText("No hay comidas registradas");
            } else {
                registros.setText(sb.toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void regresarConsultas(View view) {
        finish();
    }
}