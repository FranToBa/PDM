package com.example.learnenjoywearos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;


// Clase principal en la que podemos acceder al contenido de teoróa o ingresar el nombre para realizar una prueba
public class MainActivity extends WearableActivity {

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        setAmbientEnabled();
    }
    // Función indice que usará el botón de contenido, iniciando la actividad del índice
    public void Indice(View view){
        Intent intent = new Intent(this, MainActivity2_Indice.class);
        startActivity(intent);
        finish();
    }

    /* Función para modificar el botón "atrás".
    En este caso creamos un cuadro de diálogo en el que indicamos si desea salir o cancelar la acción.
    En caso de salir, creamos un intent para salir de la aplicación. Si cancelamos, se cierra el cuadro.*/
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