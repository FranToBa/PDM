package com.example.fitenjoy;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class fin_entrenamiento extends AppCompatActivity {
    //Variables para asignar nuestras views
    TextView resultados, t_dificultad;
    //Variables para recoger datos
    ArrayList<String> ejercicios, tiempos;
    String dificultad, texto_resultado="",texto_resultado_bd="",dificultad_text, tipo;
    int indice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_entrenamiento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Asignamos nuestras views
        resultados = (TextView) findViewById(R.id.resultados_entrenamiento);
        t_dificultad = (TextView) findViewById(R.id.dificultad);

        //Recogemos los parámetros de los ejercicios
        ejercicios = (ArrayList<String>) getIntent().getSerializableExtra("ejercicios");
        tiempos = (ArrayList<String>) getIntent().getSerializableExtra("tiempos");
        dificultad = (String) getIntent().getStringExtra("dificultad");
        tipo = (String) getIntent().getStringExtra("tipo");

        //Creamos las cadenas para mostrar
        dificultad_text = tipo + " (" + dificultad + ")";
        //Para cada ejercicio, añadimos a la cadena resutado el nombre y la duración
        for(String ejercicio: ejercicios){
            texto_resultado += getString(getResources().getIdentifier(ejercicio, "string", getPackageName()));
            if(tiempos.size()>indice) {
                texto_resultado += " (" + tiempos.get(indice) + "s)\n\n";
            }else{
                texto_resultado += " (No realizado)\n\n";
            }
            indice++;
        }
        //Asiganmos las cadenas
        t_dificultad.setText(dificultad_text);
        resultados.setText(texto_resultado);
    }

    //Función para guardar un entrenamiento
    public void guardar_entrenamiento(View view){
        Intent intent = new Intent(this, MainActivity.class);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("¿Seguro qué quiere guardar el entrenamiento y salir?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Guardamos la fecha actual con formato en un string
                        long ahora = System.currentTimeMillis();
                        Date fecha = new Date(ahora);
                        DateFormat df = new SimpleDateFormat("dd/MM/yy");
                        String salida = df.format(fecha);
                        // Creamos nuestra instancia a la base de datos e insertamos los datos.

                        SQLiteDatabase BD = admin.getWritableDatabase();
                        ContentValues insertar = new ContentValues();
                        insertar.put("fecha", salida);
                        insertar.put("dificultad", dificultad);
                        insertar.put("tipo", tipo);
                        indice=0;
                        //Para cada ejercicio añadimos una cadena con el nombre y la duración
                        for(String ejercicio: ejercicios){
                            texto_resultado_bd += getString(getResources().getIdentifier(ejercicio, "string", getPackageName()));
                            if(tiempos.size()>indice) {
                                texto_resultado_bd += " (" + tiempos.get(indice) + "s).  ";
                            }else{
                                texto_resultado_bd += " (No realizado).  ";
                            }
                            indice++;
                        }
                        insertar.put("entrenamiento", texto_resultado_bd);
                        BD.insert("entrenamientos",null,insertar);
                        BD.close();

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

    //Función para volver al menú sin guardar el entrenamiento
    public void volver_menu(View view){

        Intent intent = new Intent(this, MainActivity.class);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("¿Seguro qué desea volver al menú sin guardar el entrenamiento?")
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

    //Opción para administrar la flecha atrás de la barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage("¿Seguro qué desea volver al menú sin guardar el entrenamiento?")
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