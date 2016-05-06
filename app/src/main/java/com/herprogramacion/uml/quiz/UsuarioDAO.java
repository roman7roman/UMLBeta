package com.herprogramacion.uml.quiz;

import android.database.sqlite.SQLiteDatabase;



public interface UsuarioDAO {
    void agregar(Usuario usuario, SQLiteDatabase db);
}
