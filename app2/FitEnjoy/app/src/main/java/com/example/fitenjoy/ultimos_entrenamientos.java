package com.example.fitenjoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

public class ultimos_entrenamientos extends AppCompatActivity {
    //Variable para views y variables para datos
    private TextView tv_entrenamientos;
    String fecha, dificultad, tipo, entrenamiento, cadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_entrenamientos);
        tv_entrenamientos=(TextView)findViewById(R.id.entrenamientos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creamos la instancia a la BD, hacemos la consulta y la pasamos al TextView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        Cursor consulta = BD.rawQuery("select * from entrenamientos",null);
        //Mientras haya elementos, crear un nuevo ejercicio
        while(consulta.moveToNext()){
            fecha=consulta.getString(0);
            dificultad=consulta.getString(1);
            tipo=consulta.getString(2);
            entrenamiento=consulta.getString(3);
            cadena += fecha + ": " + tipo + " (" + dificultad + ")\n" + entrenamiento + "\n\n";
        }
        BD.close();
        //Mostramos la cadena
        tv_entrenamientos.setText(cadena);
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