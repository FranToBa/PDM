package com.example.fit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView entrenamiento;
    String fecha, tiempo, cadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrenamiento = (TextView) findViewById(R.id.ult_entrenamiento);

        //Creamos la instancia a la BD, hacemos la consulta y la pasamos al TextView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("select * from entrenamientos",null);
        if(consulta.moveToLast()) {
            fecha = consulta.getString(0);
            tiempo = consulta.getString(1);
            cadena += "Ultimo entrenamiento:\n Fecha: " + fecha + "\n Duración: " + tiempo;
        }
        BD.close();
        entrenamiento.setText(cadena);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void iniciar(View view){
        Intent intent = new Intent(this, entrenamiento.class);
        startActivity(intent);
        finish();
    }
}