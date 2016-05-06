package com.herprogramacion.uml.quiz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void agregar(Usuario usuario, SQLiteDatabase db) {
        ContentValues values= new ContentValues();
        values.put(DbHelper.NAME, usuario.getNombre());
        values.put(DbHelper.PASSWORD, usuario.getContrasena());
        db.insert(DbHelper.TABLE_NAME_1, null, values);
    }
}
