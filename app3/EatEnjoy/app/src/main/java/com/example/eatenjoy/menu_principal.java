package com.example.eatenjoy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class menu_principal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);
    }

    //Asiganmos nuestro menu a la barra de herramientas
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_barra,menu);
        return true;
    }

    //Funciones para elegir el tipo de comida
    //Pasamos a la siguiente actividad el nombre elegido

    public void pizzas(View view){
        Intent intent = new Intent(this, eleccion_menu.class);
        intent.putExtra("tipo_menu", "pizzas");
        startActivity(intent);
        finish();
    }

    public void hamburguesas(View view){
        Intent intent = new Intent(this, eleccion_menu.class);
        intent.putExtra("tipo_menu", "hamburguesas");
        startActivity(intent);
        finish();
    }

    public void kebabs(View view){
        Intent intent = new Intent(this, eleccion_menu.class);
        intent.putExtra("tipo_menu", "kebabs");
        startActivity(intent);
        finish();
    }

    public void perritos(View view){
        Intent intent = new Intent(this, eleccion_menu.class);
        intent.putExtra("tipo_menu", "perritos");
        startActivity(intent);
        finish();
    }


    //Opción para administrar la flecha atrás de la barra, acceso al historial y al carrito
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item_carrito:
                Intent intent2 = new Intent(this, carrito.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.item_historial:
                Intent intent3 = new Intent(this, mis_pedidos.class);
                startActivity(intent3);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    // Función para regresar al índice al pulsar "atrás".
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