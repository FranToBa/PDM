package com.example.fitenjoy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class entrenamiento extends AppCompatActivity {

    //Variables para recoger datos
    ArrayList<String> ejercicios, tiempos;
    String dificultad;
    String nombre, descripcion, tipo;
    //Variables para asignar nuestra views
    TextView nombre_ejercicio, descripcion_ejercicio;
    ImageView imagen_ejercicio,b_siguiente;
    Chronometer cronometro;
    boolean cronometrando;
    //Variables para el cronometro
    long pararse;
    int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Asignamos nuestras views
        nombre_ejercicio = (TextView) findViewById(R.id.nombre_ejercicio);
        descripcion_ejercicio = (TextView) findViewById(R.id.descripcion_ejercicio);
        imagen_ejercicio = (ImageView) findViewById(R.id.imagen_ejercicio);
        b_siguiente = (ImageView) findViewById(R.id.b_siguiente);
        cronometro =(Chronometer) findViewById(R.id.cronometro);

        //Recogemos los parámetros de los ejercicios
        ejercicios = (ArrayList<String>) getIntent().getSerializableExtra("ejercicios");
        tiempos = (ArrayList<String>) getIntent().getSerializableExtra("tiempos");
        indice = (Integer) getIntent().getIntExtra("indice", 0);
        dificultad = (String) getIntent().getStringExtra("dificultad");
        tipo = (String) getIntent().getStringExtra("tipo");
        //Obtenemos el nombre del ejercicio y creamos una cadena para más tarde obtnener su descripción
        nombre = ejercicios.get(indice);
        descripcion = "descripcion_" + nombre;

        //Obtenmos los valores del nombre, descripción e imagen de los recursos STRING y DRAWABLE
        //Asignamos esos valores a nuestras views
        nombre_ejercicio.setText(getString(getResources().getIdentifier(nombre, "string", getPackageName())));
        descripcion_ejercicio.setText(getString(getResources().getIdentifier(descripcion, "string", getPackageName())));
        imagen_ejercicio.setImageResource(getResources().getIdentifier(nombre, "drawable", getPackageName()));
        //En el último ejercicio, mostrar solo el botón de finalizar entrenamiento
        if (ejercicios.size() == (indice + 1)) {
            b_siguiente.setVisibility(View.GONE);
        }
    }

    //Función para iniciar crono
    public void iniciar_crono(View view){
        if(!cronometrando){
            cronometro.setBase(SystemClock.elapsedRealtime() - pararse);
            cronometro.start();
            cronometrando=true;
        }
    }

    //Función para parar crono
    public void parar_crono(View view){
        if(cronometrando){
            cronometro.stop();
            pararse = SystemClock.elapsedRealtime() - cronometro.getBase();
            cronometrando=false;
        }
    }

    //Función para restablecer el crono
    public void restablecer_crono(View view){
        cronometro.setBase(SystemClock.elapsedRealtime());
        pararse=0;
    }

    // Función para pasar al siguiente ejercicio
    // Añadimos el tiempo de este ejercicio al vector de tiempos
    // Pasamos todos los parámetros necesarios al siguiente ejercicio
    public void siguiente(View view){
        indice++;
        tiempos.add((String) cronometro.getText());
        Intent intent = new Intent(this, entrenamiento.class);
        intent.putExtra("ejercicios", ejercicios);
        intent.putExtra("tiempos", tiempos);
        intent.putExtra("indice", indice);
        intent.putExtra("dificultad", dificultad);
        intent.putExtra("tipo",tipo);
        startActivity(intent);
        finish();
    }

    // Función para pasar finalizar entrenamiento
    // Añadimos el tiempo de este ejercicio al vector de tiempos
    // Pasamos todos los parámetros necesarios para finalizar entrenamiento

    public void finalizar(View view){
        tiempos.add((String) cronometro.getText());
        Intent intent = new Intent(this, fin_entrenamiento.class);
        intent.putExtra("ejercicios", ejercicios);
        intent.putExtra("tiempos", tiempos);
        intent.putExtra("dificultad", dificultad);
        intent.putExtra("tipo",tipo);
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
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage("¿Seguro qué quiere finalizar el entrenamiento?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Función para modificar el botón "atrás".
    En este caso creamos un cuadro de diálogo en el que indicamos si desea salir o cancelar la acción.
    En caso de salir, creamos un intent para volver al menuú. Si cancelamos, se cierra el cuadro. */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            Intent intent = new Intent(this, MainActivity.class);
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setMessage("¿Seguro qué quiere finalizar el entrenamiento?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}