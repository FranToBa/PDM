package com.example.fitenjoy;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

public class intermedio_indice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_indice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Funciones asignadas a los botones para ir a las diferentes tipos de ejercicio

    public void Abdominales(View view){
        Intent intent = new Intent(this, intermedio_ejercicios_abdominales.class);
        startActivity(intent);
        finish();
    }
    public void Empuje(View view){
        Intent intent = new Intent(this, intermedio_ejercicios_empuje.class);
        startActivity(intent);
        finish();
    }
    public void Pierna(View view){
        Intent intent = new Intent(this, intermedio_ejercicios_pierna.class);
        startActivity(intent);
        finish();
    }
    public void Traccion(View view){
        Intent intent = new Intent(this, intermedio_ejercicios_traccion.class);
        startActivity(intent);
        finish();
    }

    //Opción para administrar la flecha atrás de la barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, indice_dificultad.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Función para regresar al índice al pulsar "atrás".
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            Intent intent = new Intent(this, indice_dificultad.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}