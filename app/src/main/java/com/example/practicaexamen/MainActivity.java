package com.example.practicaexamen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnaltas, btnbajas, btncambios, btnconsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnaltas = findViewById(R.id.altas);
        btnbajas = findViewById(R.id.bajas);
        btncambios = findViewById(R.id.cambios);
        btnconsultas = findViewById(R.id.consultas);
    }

    public void agregar(View view) {
        startActivity(new Intent(this, Altas.class));
    }

    public void cambiar(View view) {
        startActivity(new Intent(this, Cambios.class));
    }

    public void consultar(View view) {
        startActivity(new Intent(this, Consultas.class));
    }

    public void eliminar(View view) {
        startActivity(new Intent(this, Eliminar.class));
    }
}