package com.example.learnenjoy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// Clase para la primera pregunta de la prueba
public class MainActivity2_p1 extends AppCompatActivity {
    //Variables para asignar nuestras views
    private TextView tv_nombre, tv_calificacion;
    private ImageView iv_parte;
    private EditText et_respuesta;


    int calificacion=0, num_aleatorio; //Variables para las funciones
    String nombre_jugador; //Mandar el nombre a la siguiente activity
    String partes [] = {"codo", "hombro", "rodilla"}; //String para seleccionar una pregunta

    // Función onCreate para asignar nuestras views, obtener el nombre del jugador y llamar a parteAleatoria para generar pregunta.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_p1);

        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        tv_calificacion = (TextView)findViewById(R.id.textView_calificacion);
        iv_parte = (ImageView)findViewById(R.id.imageView_preguntaparte);
        et_respuesta = (EditText)findViewById(R.id.editTextText_respuesta);

        nombre_jugador = getIntent().getStringExtra("nombre");
        tv_nombre.setText(nombre_jugador);
        parteAleatoria();

    }

    /* Esta función es la encargada de generar una pregunta diferente cada vez que se realice una prueba.
    Generamos un número aleatorio del 0 al 2 y seleccionamos ese valor en el string creado sobre posibles partes del cuerpo.
    Dependiendo el seleccionado, obtenemos la imagen de esa parte y la pasamos a nuesta ImageView. */
    public void parteAleatoria(){
        int id;
        num_aleatorio = (int) (Math.random()*3);
        if(num_aleatorio == 0){
            id = getResources().getIdentifier(partes[0],"drawable", getPackageName());
            iv_parte.setImageResource(id);
        }else if(num_aleatorio == 1){
            id = getResources().getIdentifier(partes[1],"drawable", getPackageName());
            iv_parte.setImageResource(id);
        }else if(num_aleatorio == 2){
            id = getResources().getIdentifier(partes[2],"drawable", getPackageName());
            iv_parte.setImageResource(id);
        }
    }

    /* Función encargada de comprar si la respuesta es correcta o no.
    Se crea un cuadro de diálogo en el que se dirá si ha acertado y cual ha sido la respiuesta correcta.*/
    public void CompararRespuesta(View view){
        String respuesta = et_respuesta.getText().toString();
        String dialog_message="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = new Intent(this, MainActivity2_p2.class);
        intent.putExtra("nombre", nombre_jugador);

        if(!respuesta.equals("")){
            /* Dependiendo de la pregunta que hemos planteado, comparamos la respuesta obtenida con diferentes formas de respuesta.
            En caso de acertar, aumentamos la calificación. Creamos el mensaje para mostrar en el cuadro de diálogo. */
            if(num_aleatorio==0){
                    if(respuesta.equals("codo") || respuesta.equals("Codo") || respuesta.equals("CODO")){
                        calificacion++;
                        dialog_message = "¡CORRECTO! La respuesta correcta era CODO.";
                    } else {
                        dialog_message = "¡INCORRECTO! La respuesta correcta era CODO.";
                    }
            } else if(num_aleatorio==1){
                if(respuesta.equals("hombro") || respuesta.equals("Hombro") || respuesta.equals("HOMBRO")){
                    calificacion++;
                    dialog_message = "¡CORRECTO! La respuesta correcta era HOMBRO.";
                } else {
                    dialog_message = "¡INCORRECTO! La respuesta correcta era HOMBRO.";
                }
            } else if(num_aleatorio==2){
                if(respuesta.equals("rodilla") || respuesta.equals("Rodilla") || respuesta.equals("RODILLA")){
                    calificacion++;
                    dialog_message = "¡CORRECTO! La respuesta correcta era RODILLA.";
                } else {
                    dialog_message = "¡INCORRECTO! La respuesta correcta era RODILLA.";
                }
            }
            //Creamos el cuadro de diálogo con el mensaje creado, permitiendo seguir solo si se pulsa aceptar.
            builder.setMessage(dialog_message);
            intent.putExtra("calificacion", calificacion);
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
        // Si no se ha contestado la pregunta, indicar
        } else {
            Toast.makeText(this, "Inserta una respuesta", Toast.LENGTH_SHORT).show();
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