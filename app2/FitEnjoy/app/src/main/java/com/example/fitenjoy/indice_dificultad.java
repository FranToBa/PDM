package com.example.fitenjoy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

public class indice_dificultad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indice_dificultad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Funciones asignadas a los botones para ir a las diferentes difucultades de entrenamiento

    public void Principiante(View view){
        Intent intent = new Intent(this, principiante_ejercicios.class);
        startActivity(intent);
        finish();
    }
    public void Intermedio(View view){
        Intent intent = new Intent(this, intermedio_indice.class);
        startActivity(intent);
        finish();
    }
    public void Experto(View view){
        Intent intent = new Intent(this, experto_indice.class);
        startActivity(intent);
        finish();
    }

    //Opción para administrar la flecha atrás de la barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}