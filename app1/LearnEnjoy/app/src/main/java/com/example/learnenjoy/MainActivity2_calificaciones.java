package com.example.learnenjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

// Clase para ver las calificaciones
public class MainActivity2_calificaciones extends AppCompatActivity {

    String nombres="", notas="", calificaciones="";
    private TextView tv_notas;

    // Función onCreate para obtener los datos de la base de datos y pasarlos a nuestra View
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_calificaciones);
        tv_notas=(TextView)findViewById(R.id.tv_notas);

        //Creamos la instancia a la BD, hacemos la consulta y la pasamos al TextView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        Cursor consulta = BD.rawQuery("select * from calificaciones",null);
        //Mientras haya elementos, añadirlos
        while(consulta.moveToNext()){
            nombres=consulta.getString(0);
            notas=consulta.getString(1);
            calificaciones += nombres;
            calificaciones += ": ";
            calificaciones += notas;
            calificaciones += "\n";
        }
        BD.close();
        tv_notas.setText(calificaciones);
    }

    // Función para regresar al menu al pulsar "atrás".
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