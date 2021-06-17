package com.example.eatenjoy;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //Variables para asignar nuestras views
    EditText et_direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);

        //Asignamos nuestras views
        et_direccion = (EditText) findViewById(R.id.et_direccion);

        //Consultamos de la bd la direccion
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        Cursor consulta = BD.rawQuery("select * from direccion",null);

        //Si hay direccion en la bd, ponerla como valor del TextView
        if(consulta.moveToFirst()){
            et_direccion.setText(consulta.getString(0));
        }

        BD.close();

    }

    //Función para comenzar con el pedido
    // Si se ha introducido dirrecion, actualizarla
    // Si no hay direccion se crea un cuadro de dialogo indicandolo.
    public void comenzar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        String dir = et_direccion.getText().toString();
        String dialog_message="";

        if(!dir.equals("")) {
            ContentValues insertar = new ContentValues();
            BD.delete("direccion", null, null);
            insertar.put("direccion", dir);
            BD.insert("direccion",null,insertar);
            Intent intent = new Intent(this, menu_principal.class);
            startActivity(intent);
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            dialog_message = "Debe introducir una direccion";
            builder.setMessage(dialog_message);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        BD.close();

    }

    /* Función para modificar el botón "atrás".
    En este caso creamos un cuadro de diálogo en el que indicamos si desea salir o cancelar la acción.
    En caso de salir, creamos un intent para salir de la aplicación. Si cancelamos, se cierra el cuadro. */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setMessage("¿Seguro qué quiere salir de Eat&Enjoy?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
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
        return super.onKeyDown(keyCode, event);
    }
}



