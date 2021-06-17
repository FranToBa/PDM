package com.example.eatenjoy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class producto extends AppCompatActivity {

    String tipo_menu, tamano_menu, titulo_producto, imagen_producto, descripcion_producto, precio_producto;
    String[] items = new String[]{"Coca-cola", "Fanta", "Aquarius", "Agua"};
    //Variables para asignar nuestras views
    TextView titulo, descripcion, precio, carrito;
    ImageView imagen;
    Spinner dropdown;
    LinearLayout carrito_layout;
    EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);

        //Creamos el spinner de bebidas
        dropdown = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Asignamos nuestras views
        titulo = (TextView) findViewById(R.id.carrito_titulo);
        imagen = (ImageView) findViewById(R.id.foto_menu);
        descripcion = (TextView) findViewById(R.id.descripcion_producto);
        precio = (TextView) findViewById(R.id.precio_producto);
        carrito = (TextView) findViewById(R.id.textView12);
        carrito_layout = findViewById(R.id.ln_carrito);
        nota = (EditText) findViewById(R.id.et_nota);


        //Recogemos los parámetros del menu
        tipo_menu = (String) getIntent().getStringExtra("tipo_menu");
        tamano_menu = (String) getIntent().getStringExtra("tamaño_menu");

        //Creamos las cadenas para después obtener los recursos
        titulo_producto = "menu_" + tipo_menu + "_" + tamano_menu;
        imagen_producto = "menu_" + tipo_menu;
        descripcion_producto = "descripcion_" + tipo_menu + "_" + tamano_menu;
        precio_producto = "precio_" + tipo_menu + "_" + tamano_menu;

        //Obtenemos los recursos a partir de las cadenas anteriores
        precio_producto = getString(getResources().getIdentifier(precio_producto, "string", getPackageName())) + " €";

        titulo.setText(getString(getResources().getIdentifier(titulo_producto, "string", getPackageName())));
        imagen.setImageResource(getResources().getIdentifier(imagen_producto, "drawable", getPackageName()));
        descripcion.setText(getString(getResources().getIdentifier(descripcion_producto, "string", getPackageName())));
        precio.setText(precio_producto);

        //Accedemos a los elementos del carrito y si hay mas de 4, deshabilitamos el botón de añadir al carrito
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("select * from compra",null);
        //Mientras haya elementos, añadirlos
        if(consulta.getCount() >= 4){
            carrito.setText("Carrito lleno ");
            carrito_layout.setBackgroundResource(R.drawable.shape_add_disabled);
            carrito_layout.setEnabled(false);
            precio.setText("(Max 4)");

        }

        BD.close();

    }


    //Función para añadir el producto al carrito.
    //Añadimos el pedido a la bd compra
    public void añadir_carrito(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        ContentValues insertar = new ContentValues();
        insertar.put("tipo", tipo_menu);
        insertar.put("tamano", tamano_menu);
        insertar.put("bebida", dropdown.getSelectedItem().toString());
        insertar.put("nota", nota.getText().toString());
        BD.insert("compra",null,insertar);
        BD.close();
        Intent intent = new Intent(this, carrito.class);
        startActivity(intent);
        finish();

    }

    //Asiganmos nuestro menu a la barra de herramientas
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_barra,menu);
        return true;
    }

    //Opción para administrar la flecha atrás de la barra, acceso al historial y al carrito
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, eleccion_menu.class);
                intent.putExtra("tipo_menu", tipo_menu);
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
            Intent intent = new Intent(this, eleccion_menu.class);
            intent.putExtra("tipo_menu", tipo_menu);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}