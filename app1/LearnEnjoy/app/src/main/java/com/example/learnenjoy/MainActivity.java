package com.example.learnenjoy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


// Clase principal en la que podemos acceder al contenido de teoróa o ingresar el nombre para realizar una prueba
public class MainActivity extends AppCompatActivity {
    private EditText et_n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_n = (EditText)findViewById(R.id.editTextTextPersonName);

    }

    // Función Contenido que usará el botón de contenido, iniciando la actividad del índice
    public void Contenido(View view){
        Intent intent = new Intent(this, MainActivity2_Indice.class);
        startActivity(intent);
        finish();
    }

    /* Función que pasamos al botón de Prueba.
    Se comprueba que se ha introducido un nombre, en ese caso lo pasamos a la clase del examen.
    En caso contrario, se indica que se debe introducir un nombre. */
    public void Prueba(View view){
        String nombre = et_n.getText().toString();
        String dialog_message="";
        if(!nombre.equals("")) {
            Intent intent = new Intent(this, MainActivity_contrasena_examen.class);
            intent.putExtra("nombre", nombre);
            startActivity(intent);
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            dialog_message = "Debe introducir un nombre.";
            builder.setMessage(dialog_message);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /* Función que pasamos al botón de Prueba.
    Se comprueba que se ha introducido un nombre, en ese caso lo pasamos a la clase del examen.
    En caso contrario, se indica que se debe introducir un nombre. */
    public void Calificaciones(View view){
        String nombre = et_n.getText().toString();
        Intent intent = new Intent(this, MainActivity_contrasena_profesor.class);
        intent.putExtra("nombre", nombre);
        startActivity(intent);
        finish();

    }

    /* Función para modificar el botón "atrás".
    En este caso creamos un cuadro de diálogo en el que indicamos si desea salir o cancelar la acción.
    En caso de salir, creamos un intent para salir de la aplicación. Si cancelamos, se cierra el cuadro. */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setMessage("¿Seguro qué quiere salir de Learn&Enjoy?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
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