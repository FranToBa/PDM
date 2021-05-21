package com.example.fitenjoy;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class experto_ejercicios_espalda extends AppCompatActivity {
    //Variables para asignar nuestras views
    private CheckBox ej1, ej2, ej3, ej4, ej5, ej6;
    //Arrays a los que pasaremos los ejercicios seleccionados y los tiempos de cada ejercicio
    ArrayList<String> ejercicios = new ArrayList<String>();
    ArrayList<String> tiempos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experto_ejercicios_espalda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Asignamos nuestras views
        ej1 = (CheckBox) findViewById(R.id.cb_plancha);
        ej2 = (CheckBox) findViewById(R.id.cb_plancha_lateral);
        ej3 = (CheckBox) findViewById(R.id.cb_encogimientos_abd);
        ej4 = (CheckBox) findViewById(R.id.cb_encogimientos_pelvis);
        ej5 = (CheckBox) findViewById(R.id.cb_encogimientos_tronco);
        ej6 = (CheckBox) findViewById(R.id.cb_dead_bug);

        //Damos funcionalidad a cada checkbox de los ejercicios, añadiendolo al vector si seleccionamos y eliminándolo si lo quitamos.
        ej1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("dominadas");
                }else{
                    ejercicios.remove("dominadas");
                }
            }
        });

        ej2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("polea_pecho");
                }else{
                    ejercicios.remove("polea_pecho");
                }
            }
        });

        ej3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("peso_muerto");
                }else{
                    ejercicios.remove("peso_muerto");
                }
            }
        });

        ej4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("remo");
                }else{
                    ejercicios.remove("remo");
                }
            }
        });

        ej5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("remo_mancuerna");
                }else{
                    ejercicios.remove("remo_mancuerna");
                }
            }
        });
        ej6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("lumbares");
                }else{
                    ejercicios.remove("lumbares");
                }
            }
        });

    }

    //Función para empezar el entrenamiento
    //Pasamos los tiempos, los ejercicios y la dificultad.
    // Debe haber selccionado al menos un ejercicio.
    public void ComenzarEntrenamiento(View view){
        if(ejercicios.size()>0){
            Intent intent = new Intent(this, entrenamiento.class);
            intent.putExtra("ejercicios", ejercicios);
            intent.putExtra("tiempos", tiempos);
            intent.putExtra("indice",0);
            intent.putExtra("dificultad","Experto");
            intent.putExtra("tipo","Espalda");
            startActivity(intent);
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String dialog_message = "Debe seleccionar al menos un ejercicio.";
            builder.setMessage(dialog_message);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    //Opción para administrar la flecha atrás de la barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, experto_indice.class);
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
            Intent intent = new Intent(this, experto_indice.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}