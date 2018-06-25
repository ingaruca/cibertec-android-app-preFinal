package com.dev.softsky.prefinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD_SQLite extends SQLiteOpenHelper {

    public BD_SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE Alumnos(_ID text primary key, Nombre text, Apellido text, Correo text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    //Métodos CRUD

    //CREATE
    public void registrarAlumno(String id, String nombre, String apellido, String correo){
        ContentValues valores = new ContentValues();
        valores.put("_ID", id);
        valores.put("Nombre", nombre);
        valores.put("Apellido", apellido);
        valores.put("Correo", correo);

        this.getWritableDatabase().insert("Alumnos", null, valores);
    }

    //READ
    public Cursor listarAlumnos(){
        Cursor cursor = this.getReadableDatabase().query("Alumnos", new String[]{"_ID", "Nombre", "Apellido", "Correo"}, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    //UPDATE
    public void editarAlumno(String id, String nombre, String apellido, String correo){
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nombre);
        valores.put("Apellido", apellido);
        valores.put("Correo", correo);

        this.getWritableDatabase().update("Alumnos", valores, "_ID='" + id + "'", null);
    }

    //DELETE
    public void eliminarAlumno(String id){
        this.getWritableDatabase().delete("Alumnos", "_ID='" + id + "'", null);
    }

    //Método Buscar
    public Cursor buscarAlumno(String id) throws SQLException{
        String[] campos = new String[]{"_ID", "Nombre", "Apellido", "Correo"};
        Cursor datos = null;

        datos = this.getReadableDatabase().query("Alumnos", campos, "_ID='" + id + "'", null, null, null, null, null);

        return datos;
    }

}
