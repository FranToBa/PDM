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

// Clase para la quinta pregunta de la prueba
public class MainActivity2_p5 extends AppCompatActivity {
    //Variables para asignar nuestras views
    private TextView tv_nombre, tv_calificacion;
    private ImageView iv_parte, iv_parte2;
    private EditText et_respuesta;

    int calificacion=0, num_aleatorio1, num_aleatorio2; //Variables para las funciones
    String nombre_jugador, mezcla; //Mandar el nombre a la siguiente activity y generar mezcla
    String colores [] = {"rojo", "verde", "azul"}; //String para seleccionar una pregunta

    // Función onCreate para asignar nuestras views, obtener el nombre del jugador y llamar a mezclaAleatoria para generar pregunta.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_p5);

        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        tv_calificacion = (TextView)findViewById(R.id.textView_calificacion);
        iv_parte = (ImageView)findViewById(R.id.imageView_preguntaparte);
        iv_parte2 = (ImageView)findViewById(R.id.imageView_preguntaparte2);
        et_respuesta = (EditText)findViewById(R.id.editTextText_respuesta);

        nombre_jugador = getIntent().getStringExtra("nombre");
        calificacion = getIntent().getIntExtra("calificacion",0);
        tv_calificacion.setText("Pregunta: 5/5");
        tv_nombre.setText(nombre_jugador);
        mezclaAleatoria();

    }

    // Este método se encarga de generar dos colores (diferentes) y generar la mezcla.
    public void mezclaAleatoria(){
        int id;
        num_aleatorio1 = (int) (Math.random()*3);
        num_aleatorio2 = (int) (Math.random()*3);
        if(num_aleatorio1!=num_aleatorio2){
            //Asignamos el primer color dependiendo del numero aleatorio obtenido
            if(num_aleatorio1 == 0){
                id = getResources().getIdentifier(colores[0],"drawable", getPackageName());
                iv_parte.setImageResource(id);
            }else if(num_aleatorio1 == 1){
                id = getResources().getIdentifier(colores[1],"drawable", getPackageName());
                iv_parte.setImageResource(id);
            }else if(num_aleatorio1 == 2){
                id = getResources().getIdentifier(colores[2],"drawable", getPackageName());
                iv_parte.setImageResource(id);
            }

            /* Asignamos el segundo color dependiendo del número aleatorio obtenido.
            Además, también comprobamos el valor del primer color y establecemos el valor de la mezcla.*/
            if(num_aleatorio2 == 0){
                id = getResources().getIdentifier(colores[0],"drawable", getPackageName());
                iv_parte2.setImageResource(id);
                if(num_aleatorio1==1){
                    mezcla = "Amarillo";
                } else if(num_aleatorio1==2){
                    mezcla = "Rosa";
                }
            }else if(num_aleatorio2 == 1){
                id = getResources().getIdentifier(colores[1],"drawable", getPackageName());
                iv_parte2.setImageResource(id);
                if(num_aleatorio1==0){
                    mezcla = "Amarillo";
                } else if(num_aleatorio1==2){
                    mezcla = "Cyan";
                }
            }else if(num_aleatorio2 == 2){
                id = getResources().getIdentifier(colores[2],"drawable", getPackageName());
                iv_parte2.setImageResource(id);
                if(num_aleatorio1==0){
                    mezcla = "Rosa";
                } else if(num_aleatorio1==1){
                    mezcla = "Cyan";
                }
            }

        // Si se han obtenido dos colores iguales, volver a llamar al método hasta obtener dos diferentes.
        } else
            mezclaAleatoria();

    }

    /* Función encargada de comprar si la respuesta es correcta o no.
    Se crea un cuadro de diálogo en el que se dirá si ha acertado y cual ha sido la respiuesta correcta.*/
    public void CompararRespuesta(View view){
        String respuesta = et_respuesta.getText().toString();
        String dialog_message="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = new Intent(this, MainActivity2_fin.class);
        intent.putExtra("nombre", nombre_jugador);

        if(!respuesta.equals("")){
            /* Dependiendo de la pregunta que hemos planteado, comparamos la respuesta obtenida con diferentes formas de respuesta.
            En caso de acertar, aumentamos la calificación. Creamos el mensaje para mostrar en el cuadro de diálogo. */
            if(mezcla.equals("Amarillo")){
                if(respuesta.equals("Amarillo") || respuesta.equals("amarillo") || respuesta.equals("AMARILLO")
                   || respuesta.equals("Marron") || respuesta.equals("marron") || respuesta.equals("MARRON")
                   || respuesta.equals("Marrón") || respuesta.equals("marrón") || respuesta.equals("MARRóN") ){
                    calificacion++;
                    dialog_message = "¡CORRECTO! La respuesta correcta era AMARILLO/MARRÓN.";
                } else {
                    dialog_message = "¡INCORRECTO! La respuesta correcta era AMARILLO/MARRÓN.";
                }
            } else if(mezcla.equals("Rosa")){
                if(respuesta.equals("rosa") || respuesta.equals("Rosa") || respuesta.equals("ROSA")
                        || respuesta.equals("MAGENTA")|| respuesta.equals("Magenta")|| respuesta.equals("magenta")
                        || respuesta.equals("MORADO")|| respuesta.equals("Morado")|| respuesta.equals("morado")){
                    calificacion++;
                    dialog_message = "¡CORRECTO! La respuesta correcta era ROSA/MAGENTA/MORADO.";
                } else {
                    dialog_message = "¡INCORRECTO! La respuesta correcta era ROSA/MAGENTA/MORADO.";
                }
            } else if(mezcla.equals("Cyan")){
                if(respuesta.equals("azul clarito") || respuesta.equals("Azul clarito") || respuesta.equals("AZUL CLARITO")
                        || respuesta.equals("CYAN")|| respuesta.equals("Cyan")|| respuesta.equals("cyan")
                ){
                    calificacion++;
                    dialog_message = "¡CORRECTO! La respuesta correcta era CYAN/AZUL CLARITO.";
                } else {
                    dialog_message = "¡INCORRECTO! La respuesta correcta era CYAN/AZUL CLARITO.";
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