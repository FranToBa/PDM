package com.example.fitenjoy;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Función comenzar entrenamiento, para elegir dificultad y ejercicios
    public void comenzar(View view){
        Intent intent = new Intent(this, indice_dificultad.class);
        startActivity(intent);
        finish();
    }

    // Función comenzar entrenamiento, para elegir dificultad y ejercicios
    public void ultimos_entrenamientos(View view){
        Intent intent = new Intent(this, ultimos_entrenamientos.class);
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
            builder.setMessage("¿Seguro qué quiere salir de Fit&Enjoy?")
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