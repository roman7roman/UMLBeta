package com.herprogramacion.uml.grafica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by a on 30/03/2016.
 */
public class GraficaHelperDos extends SQLiteOpenHelper {

    /**
     * CREATE TABLE Eleccion ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,
     * "sigla" TEXT NOT NULL ,
     * "nombre" TEXT NOT NULL ,
     * "votos" INTEGER NOT NULL DEFAULT 0)
     * */

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="votosdos.db";
    private static final String TABLE_NAME="Eleccion";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_SIGLA="sigla";
    private static final String COLUMN_NAME="nombre";
    private static final String COLUMN_VOTOS="votos";
    private static final String TABLE_CREATE="create table Eleccion (id integer primary key AUTOINCREMENT not null , "+
            "sigla TEXT NOT NULL , " +
            "nombre TEXT NOT NULL , " +
            "votos INTEGER not null DEFAULT 0);";
    SQLiteDatabase db;
    //datos

    /**
     * Constructor de clase
     * */
    public GraficaHelperDos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertResult(GraficaTopic g){
        db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_NAME, g.getNombre());
        values.put(COLUMN_SIGLA, g.getSigla());
        values.put(COLUMN_VOTOS, g.getVotos());
        values.put(COLUMN_ID, count);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {
        String query= "DROP TABLE IF EXIST"+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public ArrayList<GraficaTopic> getCandidatos(){

        ArrayList<GraficaTopic> partidoList = new ArrayList<GraficaTopic>();
        Cursor cursor = db.query( "Eleccion" ,
                new String[]{"id","sigla","nombre","votos"} , //columns
                null, //clausula where
                null, //The values for the WHERE clause
                null, // don't group the rows
                null, //don't filter by row groups
                null //The sort order
        );
        cursor.moveToFirst();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            GraficaTopic graficaTopic = new GraficaTopic();
            graficaTopic.setId( cursor.getInt(cursor.getColumnIndexOrThrow("id")) );
            graficaTopic.setSigla( cursor.getString(cursor.getColumnIndexOrThrow("sigla")) );
            graficaTopic.setNombre( cursor.getString(cursor.getColumnIndexOrThrow("nombre")) );
            graficaTopic.setVotos( cursor.getInt(cursor.getColumnIndexOrThrow("votos")) );
            partidoList.add(graficaTopic);
        }
        return partidoList;
    }

    /** Abre conexion a base de datos */
    public void openConnection(){
        db= this.getWritableDatabase();
    }

    /** Cierra conexion a la base de datos */
    public void closeConnection()
    {
        db.close();
    }
    public int getTotalVotos()
    {
        String sql = "SELECT SUM(votos) AS total FROM Eleccion";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        cursor.close();
        return total;
    }
}//SQLiteHelper:end