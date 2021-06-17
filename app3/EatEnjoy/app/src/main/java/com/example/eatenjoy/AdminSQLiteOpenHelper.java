package com.example.eatenjoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

// Clase para implementar nuestras acciones con la Base de Datos
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Función para la creación de la tabla.
    @Override
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL("create table compra(tipo text, tamano text, bebida text, nota text)");
        BD.execSQL("create table direccion(direccion text)");
        BD.execSQL("create table pedidos(fecha text, pedido text, precio text, estado text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

