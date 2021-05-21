package com.example.fitenjoy;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class principiante_ejercicios extends AppCompatActivity {

    //Variables para asignar nuestras views
    private CheckBox cb_spinning, cb_eliptica, cb_cinta, cb_plancha, cb_sentadillas, cb_puente;
    //Arrays a los que pasaremos los ejercicios seleccionados y los tiempos de cada ejercicio
    ArrayList<String> ejercicios = new ArrayList<String>();
    ArrayList<String> tiempos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principiante_ejercicios);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Asignamos nuestras views
        cb_spinning = (CheckBox) findViewById(R.id.cb_plancha);
        cb_eliptica = (CheckBox) findViewById(R.id.cb_plancha_lateral);
        cb_cinta = (CheckBox) findViewById(R.id.cb_encogimientos_abd);
        cb_plancha = (CheckBox) findViewById(R.id.cb_encogimientos_pelvis);
        cb_sentadillas = (CheckBox) findViewById(R.id.cb_encogimientos_tronco);
        cb_puente = (CheckBox) findViewById(R.id.cb_dead_bug);

        //Damos funcionalidad a cada checkbox de los ejercicios, añadiendolo al vector si seleccionamos y eliminándolo si lo quitamos.
        cb_spinning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("spinning");
                }else{
                    ejercicios.remove("spinning");
                }
            }
        });

        cb_eliptica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("eliptica");
                }else{
                    ejercicios.remove("eliptica");
                }
            }
        });

        cb_cinta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("cinta");
                }else{
                    ejercicios.remove("cinta");
                }
            }
        });

        cb_plancha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("plancha");
                }else{
                    ejercicios.remove("plancha");
                }
            }
        });

        cb_sentadillas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("sentadillas");
                }else{
                    ejercicios.remove("sentadillas");
                }
            }
        });
        cb_puente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ejercicios.add("puente");
                }else{
                    ejercicios.remove("puente");
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
            intent.putExtra("dificultad","Principiante");
            intent.putExtra("tipo","Mixto");
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