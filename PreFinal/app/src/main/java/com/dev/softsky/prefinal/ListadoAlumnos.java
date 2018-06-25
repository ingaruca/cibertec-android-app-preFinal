package com.dev.softsky.prefinal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoAlumnos extends AppCompatActivity {

    ListView listAlumnos;

    //Instanciar la bd
    BD_SQLite bd = new BD_SQLite(this, "bdAlumno", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alumnos);

        listAlumnos = (ListView)findViewById(R.id.listAlumnos);

        Cursor cursor;
        bd.abrir();
        cursor = bd.listarAlumnos();

        final ArrayList<String> lista = new ArrayList<String>();
        String registro = "";

        do{
            registro = "\n" +
                       "Código: " + cursor.getString(0) + "\n" +
                       "Nombre: " + cursor.getString(1) + "\n" +
                       "Apellido: " + cursor.getString(2) + "\n" +
                       "Correo: " + cursor.getString(3) + "\n";
            lista.add(registro);
        }
        while (cursor.moveToNext());

        final ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listAlumnos.setAdapter(adap);

        listAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), lista.get(i), Toast.LENGTH_LONG).show();
            }
        });
    }
}
