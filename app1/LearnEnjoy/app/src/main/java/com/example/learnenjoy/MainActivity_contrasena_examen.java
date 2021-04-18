package com.example.learnenjoy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

// Clase para pedir autenticación para realizar un examen
public class MainActivity_contrasena_examen extends AppCompatActivity {
    private EditText et_contr; //Variable para obtener la contraseña del usuario
    private String contrasena= "pruebaexamen"; //Contraseña establecida para el acceso
    String nombre_jugador;

    // Función onCreate para asignar nuestras views y obtener el nombre del jugador.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contrasena_examen);
        et_contr = (EditText)findViewById(R.id.et_contProf);
        nombre_jugador = getIntent().getStringExtra("nombre");
    }

    //Método encargado de comparar la contraseña establecida con la introducida
    public void Comprobar(View view){
        String c = et_contr.getText().toString();
        String dialog_message="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Si las contraseñas son iguales, creamos un cuadro de diálogo indicandolo y pasando a la prueba
        if(c.equals(contrasena)){
            Intent intent = new Intent(this, MainActivity2_p1.class);
            intent.putExtra("nombre", nombre_jugador);
            dialog_message = "¡CORRECTO! Pulsa aceptar para empezar la prueba.";
            builder.setMessage(dialog_message);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        //Si no es correcta la contraseña, indicarlo y permanecer en la pagina
        } else {
            dialog_message = "¡INCORRECTO! La contraseña introducida no es válida.";
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

    // Función para regresar al menú al pulsar "atrás".
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