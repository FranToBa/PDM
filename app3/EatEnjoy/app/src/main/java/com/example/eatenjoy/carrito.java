package com.example.eatenjoy;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class carrito extends AppCompatActivity {

    String tipo_pedido, imagen_pedido, precio_pedido, precio_final, cadena_precio, pedido_bd="";
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<String> precios = new ArrayList<String>();
    ArrayList<String> imagenes = new ArrayList<String>();
    ArrayList<String> bebidas = new ArrayList<String>();
    ArrayList<String> notas = new ArrayList<String>();
    double precio_total=0.0;

    //Variables para asignar nuestras views
    TextView tv1, tv2, tv3, tv4, tv_p_1, tv_p_2, tv_p_3, tv_p_4, tv_b_1, tv_b_2, tv_b_3, tv_b_4, tv_n_1, tv_n_2, tv_n_3, tv_n_4;
    ImageView i1, i2, i3, i4;
    LinearLayout ly1, ly2, ly3, ly4;
    Button b_pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);

        tv1 = (TextView) findViewById(R.id.tv_1);
        i1 = (ImageView) findViewById(R.id.foto1);
        tv_p_1 = (TextView) findViewById(R.id.tv_p_1);
        tv_b_1 = (TextView) findViewById(R.id.tv_b_1);
        tv_n_1 = (TextView) findViewById(R.id.tv_n_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        i2 = (ImageView) findViewById(R.id.foto2);
        tv_p_2 = (TextView) findViewById(R.id.tv_p_2);
        tv_b_2 = (TextView) findViewById(R.id.tv_b_2);
        tv_n_2 = (TextView) findViewById(R.id.tv_n_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        i3 = (ImageView) findViewById(R.id.foto3);
        tv_p_3 = (TextView) findViewById(R.id.tv_p_3);
        tv_b_3 = (TextView) findViewById(R.id.tv_b_3);
        tv_n_3 = (TextView) findViewById(R.id.tv_n_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        i4 = (ImageView) findViewById(R.id.foto4);
        tv_p_4 = (TextView) findViewById(R.id.tv_p_4);
        tv_b_4 = (TextView) findViewById(R.id.tv_b_4);
        tv_n_4 = (TextView) findViewById(R.id.tv_n_4);
        ly1 = (LinearLayout) findViewById(R.id.pedido1);
        ly2 = (LinearLayout) findViewById(R.id.pedido2);
        ly3 = (LinearLayout) findViewById(R.id.pedido3);
        ly4 = (LinearLayout) findViewById(R.id.pedido4);
        b_pagar = (Button) findViewById(R.id.b_pagar);



        //Creamos la instancia a la BD, hacemos la consulta y la pasamos al TextView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("select * from compra",null);

        //Mientras haya elementos, creamos las cadenas y las añadimos a los vectores
        while(consulta.moveToNext()){
            tipo_pedido= "menu_" + consulta.getString(0) + "_" + consulta.getString(1);
            imagen_pedido =  "menu_" + consulta.getString(0);
            precio_pedido =  "precio_" + consulta.getString(0) + "_" + consulta.getString(1);

            titulos.add(tipo_pedido);
            imagenes.add(imagen_pedido);
            precios.add(precio_pedido);
            bebidas.add(consulta.getString(2));
            notas.add(consulta.getString(3));

            //Vamos sumando el precio para obtener el precio total
            precio_total += Double.parseDouble(getString(getResources().getIdentifier(precio_pedido, "string", getPackageName())));

        }
        precio_final = "Realizar pedido (" + String.valueOf(precio_total) + "€)";
        b_pagar.setText(precio_final);

        BD.close();


        //Dependiendo del tamño de los vectores, vamos añadiendo los productos seleccionados
        //Para ello obtenemos los recursos usando las cadenas de los vectores y los asignamos a las views
        if(titulos.size() > 0){
            i1.setImageResource(getResources().getIdentifier(imagenes.get(0), "drawable", getPackageName()));
            tv1.setText(getString(getResources().getIdentifier(titulos.get(0), "string", getPackageName())));
            cadena_precio = getString(getResources().getIdentifier(precios.get(0), "string", getPackageName())) + "€";
            tv_p_1.setText(cadena_precio);
            tv_b_1.setText(bebidas.get(0));
            tv_n_1.setText(notas.get(0));
            ly1.setBackgroundColor(Color.parseColor("#F6F6F6"));

        }
        if(titulos.size() > 1){
            i2.setImageResource(getResources().getIdentifier(imagenes.get(1), "drawable", getPackageName()));
            tv2.setText(getString(getResources().getIdentifier(titulos.get(1), "string", getPackageName())));
            cadena_precio = getString(getResources().getIdentifier(precios.get(1), "string", getPackageName())) + "€";
            tv_p_2.setText(cadena_precio);
            tv_b_2.setText(bebidas.get(1));
            tv_n_2.setText(notas.get(1));
            ly2.setBackgroundColor(Color.parseColor("#F6F6F6"));

        }
        if(titulos.size() > 2){
            i3.setImageResource(getResources().getIdentifier(imagenes.get(2), "drawable", getPackageName()));
            tv3.setText(getString(getResources().getIdentifier(titulos.get(2), "string", getPackageName())));
            cadena_precio = getString(getResources().getIdentifier(precios.get(2), "string", getPackageName())) + "€";
            tv_p_3.setText(cadena_precio);
            tv_b_3.setText(bebidas.get(2));
            tv_n_3.setText(notas.get(2));
            ly3.setBackgroundColor(Color.parseColor("#F6F6F6"));

        }
        if(titulos.size() > 3){
            i4.setImageResource(getResources().getIdentifier(imagenes.get(3), "drawable", getPackageName()));
            tv4.setText(getString(getResources().getIdentifier(titulos.get(3), "string", getPackageName())));
            cadena_precio = getString(getResources().getIdentifier(precios.get(3), "string", getPackageName())) + "€";
            tv_p_4.setText(cadena_precio);
            tv_b_4.setText(bebidas.get(3));
            tv_n_4.setText(notas.get(3));
            ly4.setBackgroundColor(Color.parseColor("#F6F6F6"));

        }

    }


    //Función encargada de vaciar el carrito
    //Crea un cuadro de diálogo de confirmación y vacía la base de datos "compra"
    public void vaciar(View view){
        Intent intent = new Intent(this, menu_principal.class);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        builder.setMessage("¿Seguro qué desea vaciar al carrito?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BD.delete("compra", null, null);
                        BD.close();
                        startActivity(intent);
                        finish();
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

    //Función para continuar añadiendo productos al carrito
    public void continuar_compra(View view){
        Intent intent = new Intent(this, menu_principal.class);
        startActivity(intent);
        finish();

    }

    //Función encargada de realizar un pedido
    //Creamos un cuadro de diálogo para confirmar el pago
    //Se vacía el carrito eliminado los datos de la bd "compras"
    //Se añaden la fecha, el precio, los titulos, las bebidas y las notas a la bd de "pedidos"
    public void pagar(View view){
        //Guardamos la fecha actual con formato en un string
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String salida = df.format(fecha);
        Intent intent = new Intent(this, mis_pedidos.class);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        builder.setMessage("¿Seguro qué desea realizar el pedido?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BD.delete("compra", null, null);
                        ContentValues insertar = new ContentValues();
                        for(int i = 0; i < titulos.size(); i++){
                            pedido_bd += "- " + getString(getResources().getIdentifier(titulos.get(i), "string", getPackageName()));
                            pedido_bd += ". " + bebidas.get(i);
                            pedido_bd += ". " + notas.get(i) + "\n";

                        }
                        insertar.put("fecha", salida);
                        insertar.put("pedido", pedido_bd);
                        insertar.put("precio", String.valueOf(precio_total));
                        insertar.put("estado", "En proceso");
                        BD.insert("pedidos",null,insertar);
                        BD.close();
                        startActivity(intent);
                        finish();
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
            case R.id.item_historial:
                Intent intent3 = new Intent(this, MainActivity.class);
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


