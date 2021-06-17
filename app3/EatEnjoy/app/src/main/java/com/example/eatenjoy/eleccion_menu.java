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
import android.widget.ImageView;
import android.widget.TextView;

public class eleccion_menu extends AppCompatActivity {

    String tipo_menu, precio1, precio2, precio3, img;
    //Variables para asignar nuestras views
    TextView titulo, p1, p2, p3;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_menu);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);

        //Asignamos nuestras view y obtenemos las variables de la activity anterior
        titulo = (TextView) findViewById(R.id.carrito_titulo);
        imagen = (ImageView) findViewById(R.id.imagen_menu);
        p1 = (TextView) findViewById(R.id.p1);
        p2 = (TextView) findViewById(R.id.p2);
        p3 = (TextView) findViewById(R.id.p3);
        tipo_menu = (String) getIntent().getStringExtra("tipo_menu");

        //Creamos las cadenas con los nombres adecuados

        img = "menu_" + tipo_menu;
        precio1 = "precio_" + tipo_menu + "_pequeno";
        precio2 = "precio_" + tipo_menu + "_mediano";
        precio3 = "precio_" + tipo_menu + "_grande";

        //Obtenemos los recursos a través de las cadenas creadas anteriormente y las asignamos a nuestras views

        precio1 = getString(getResources().getIdentifier(precio1, "string", getPackageName())) + " €";
        precio2 = getString(getResources().getIdentifier(precio2, "string", getPackageName())) + " €";
        precio3 = getString(getResources().getIdentifier(precio3, "string", getPackageName())) + " €";

        titulo.setText(getString(getResources().getIdentifier(tipo_menu, "string", getPackageName())));
        imagen.setImageResource(getResources().getIdentifier(img, "drawable", getPackageName()));
        p1.setText(precio1);
        p2.setText(precio2);
        p3.setText(precio3);


    }

    //Funciones para seleccionar el tipo de menu elegido
    //Pasamos a la siguiente actividad el tipo de menu y su tamaño

    public void pequeño(View view){
        Intent intent = new Intent(this, producto.class);
        intent.putExtra("tipo_menu", tipo_menu);
        intent.putExtra("tamaño_menu", "pequeno");
        startActivity(intent);
        finish();
    }

    public void mediano(View view){
        Intent intent = new Intent(this, producto.class);
        intent.putExtra("tipo_menu", tipo_menu);
        intent.putExtra("tamaño_menu", "mediano");
        startActivity(intent);
        finish();
    }

    public void grande(View view){
        Intent intent = new Intent(this, producto.class);
        intent.putExtra("tipo_menu", tipo_menu);
        intent.putExtra("tamaño_menu", "grande");
        startActivity(intent);
        finish();
    }

    //Asiganmos nuestro menu a la barra de herramientas
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_barra,menu);
        return true;
    }

    //Opción para administrar la flecha atrás de la barra, acceso al historial y al carrito
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, menu_principal.class);
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
            Intent intent = new Intent(this, menu_principal.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}