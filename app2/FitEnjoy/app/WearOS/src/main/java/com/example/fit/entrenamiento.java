package com.example.fit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.activity.WearableActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Chronometer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class entrenamiento extends WearableActivity {

    Chronometer cronometro;
    boolean cronometrando;
    long pararse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);
        cronometro =(Chronometer) findViewById(R.id.cronometro);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void iniciar_crono(View view){
        if(!cronometrando){
            cronometro.setBase(SystemClock.elapsedRealtime() - pararse);
            cronometro.start();
            cronometrando=true;
        }
    }

    public void parar_crono(View view){
        if(cronometrando){
            cronometro.stop();
            pararse = SystemClock.elapsedRealtime() - cronometro.getBase();
            cronometrando=false;
        }
    }

    public void restablecer_crono(View view){
        cronometro.setBase(SystemClock.elapsedRealtime());
        pararse=0;
    }

    public void finalizar(View view){
        String tiempo = (String) cronometro.getText();
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String salida = df.format(fecha);
        // Creamos nuestra instancia a la base de datos e insertamos los datos.
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        ContentValues insertar = new ContentValues();
        insertar.put("fecha", salida);
        insertar.put("duracion", tiempo);
        BD.insert("entrenamientos",null,insertar);
        BD.close();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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