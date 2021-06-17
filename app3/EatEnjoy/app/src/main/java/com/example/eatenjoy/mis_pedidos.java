package com.example.eatenjoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class mis_pedidos extends AppCompatActivity {

    TextView pedidos;
    String fecha, pedido, precio, estado, cadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);
        pedidos =(TextView)findViewById(R.id.tv_pedidos);

        //Creamos la instancia a la BD, hacemos la consulta y la pasamos al TextView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        Cursor consulta = BD.rawQuery("select * from pedidos",null);
        //Mientras haya elementos, crear un nuevo pedido
        while(consulta.moveToNext()){
            fecha=consulta.getString(0);
            pedido=consulta.getString(1);
            precio=consulta.getString(2);
            estado=consulta.getString(3);
            cadena = fecha + "\n" + pedido + "Precio: " + precio + "€. Estado: " + estado  + "\n\n" + cadena;
        }
        BD.close();
        //Mostramos la cadena
        pedidos.setText(cadena);

    }

    //Opción para administrar la flecha atrás de la barra
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