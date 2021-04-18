package com.example.learnenjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/* Clase para visualizar la pantalla de fin del examen.
En ella podemos ver el nombre ya la calificación obtenida.*/
public class MainActivity2_fin extends AppCompatActivity {

    private TextView tv_nombre, tv_calificacion;
    String nombre_jugador;
    int calificacion;

    //Asignamos nuestras views a las variables para mostrar el nombre y la calificación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_fin);

        tv_nombre = (TextView)findViewById(R.id.textView_nombrefinal);
        tv_calificacion = (TextView)findViewById(R.id.textView_calfinal);

        nombre_jugador = getIntent().getStringExtra("nombre");
        calificacion = getIntent().getIntExtra("calificacion",0);
        calificacion = calificacion*2;
        tv_calificacion.setText("Calificación: " + calificacion + "/10");
        tv_nombre.setText(nombre_jugador);

        // Creamos nuestra instancia a la base de datos e insertamos el nombre y la calificación.
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        ContentValues insertar = new ContentValues();
        insertar.put("nombre", nombre_jugador);
        insertar.put("nota",calificacion);
        BD.insert("calificaciones",null,insertar);
        BD.close();

    }

    // Función para el botón para volver al menú principal.
    public void VolverMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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