package com.example.fitenjoy;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

public class experto_indice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experto_indice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Funciones asignadas a los botones para ir a las diferentes partes del entrenamiento

    public void Brazo(View view){
        Intent intent = new Intent(this, experto_ejercicios_brazo.class);
        startActivity(intent);
        finish();
    }
    public void Espalda(View view){
        Intent intent = new Intent(this, experto_ejercicios_espalda.class);
        startActivity(intent);
        finish();
    }
    public void Hombro(View view){
        Intent intent = new Intent(this, experto_ejercicios_hombro.class);
        startActivity(intent);
        finish();
    }
    public void Pecho(View view){
        Intent intent = new Intent(this, experto_ejercicios_pecho.class);
        startActivity(intent);
        finish();
    }
    public void Pierna(View view){
        Intent intent = new Intent(this, experto_ejercicios_pierna.class);
        startActivity(intent);
        finish();
    }

    //Opción para administrar la flecha atrás de la barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this,indice_dificultad.class);
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