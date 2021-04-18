package com.example.learnenjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

// Clase para visualizar el índice de contenidos
public class MainActivity2_Indice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__indice);
    }

    //Funciones asignadas a los botones para ir a cada una de las partes del contenido teórico.

    public void Operaciones(View view){
        Intent intent = new Intent(this, MainActivity2_c_operaciones.class);
        startActivity(intent);
        finish();
    }
    public void Formas(View view){
        Intent intent = new Intent(this, MainActivity2_c_formas.class);
        startActivity(intent);
        finish();
    }
    public void Cuerpo(View view){
        Intent intent = new Intent(this, MainActivity2_c_cuerpo.class);
        startActivity(intent);
        finish();
    }
    public void Animales(View view){
        Intent intent = new Intent(this, MainActivity2_c_animales.class);
        startActivity(intent);
        finish();
    }
    public void Colores(View view){
        Intent intent = new Intent(this, MainActivity2_c_colores.class);
        startActivity(intent);
        finish();
    }

    // Función para regresar al menú al pulsar "atrás".
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