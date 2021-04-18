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

// Clase para la tercera pregunta de la prueba
public class MainActivity2_p3 extends AppCompatActivity {
    //Variables para asignar nuestras views
    private TextView tv_nombre, tv_calificacion, tv_suma;
    private EditText et_respuesta;

    int calificacion=0, num1, num2, resultado, respuestaInt; //Variables para las funciones
    String nombre_jugador; //Mandar el nombre a la siguiente activity

    // Función onCreate para asignar nuestras views, obtener el nombre del jugador y llamar a sumaAleatoria para generar pregunta.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_p3);
        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        tv_calificacion = (TextView)findViewById(R.id.textView_calificacion);
        et_respuesta = (EditText)findViewById(R.id.editTextText_respuesta);
        tv_suma = (TextView)findViewById(R.id.textView_suma);

        nombre_jugador = getIntent().getStringExtra("nombre");
        calificacion = getIntent().getIntExtra("calificacion",0);
        tv_calificacion.setText("Pregunta: 3/5");
        tv_nombre.setText(nombre_jugador);
        sumaAleatoria();

    }

    /* Con esta función obtenemos dos números aleatorios y debemos comprobar que su suma no sea mayor de 10.
    Si los números obtenidos son validos, creamos el string para mostrarlo y guardamos el resultado.
    Si no, llamamos de nuevo al método hasta encontrar una suma válida. */
    public void sumaAleatoria(){
        int id;
        String suma="";
        num1 = (int) (Math.random()*10);
        num2 = (int) (Math.random()*10);
        if(num1+num2<=10){
            suma+=num1;
            suma+="+";
            suma+=num2;
            tv_suma.setText(suma);
            resultado = num1+num2;

        }else
            sumaAleatoria();
    }

    /* Función encargada de comprar si la respuesta es correcta o no.
    Se crea un cuadro de diálogo en el que se dirá si ha acertado y cual ha sido la respiuesta correcta.*/
    public void CompararRespuesta(View view){
        String respuesta = et_respuesta.getText().toString();
        respuestaInt = Integer.parseInt(respuesta);
        String dialog_message="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = new Intent(this, MainActivity2_p4.class);
        intent.putExtra("nombre", nombre_jugador);

        if(!respuesta.equals("")){
            //Comparamos la respuesta con el resultado. Creamos el mensaje y si es correcto, aumentamos calificación.
            if(respuestaInt == resultado ){
                calificacion++;
                dialog_message = "¡CORRECTO! La respuesta correcta era ";
                dialog_message += resultado;
            } else {
                dialog_message = "¡INCORRECTO! La respuesta correcta era ";
                dialog_message += resultado;
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