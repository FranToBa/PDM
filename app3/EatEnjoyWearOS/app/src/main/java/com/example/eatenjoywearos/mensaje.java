package com.example.eatenjoywearos;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class mensaje extends WearableActivity {

    TextView mensaje;
    String mensaje_recibido = "Su pedido llegará en 30 minutos\n\n-Menú hamburguesa mediano\n-Menú pizza grande";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        mensaje = (TextView) findViewById(R.id.tv_mensaje);

        if(mensaje_recibido!=""){
            mensaje.setText(mensaje_recibido);
        }

        // Enables Always-on
        setAmbientEnabled();
    }

    public void atrás(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}