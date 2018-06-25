package com.dev.softsky.prefinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText txtCodigoBuscar, txtCodigo, txtNombre, txtApellido, txtCorreo;
    Button btnBuscar, btnListar, btnRegistrar;

    //Instanciar la bd
    BD_SQLite bd = new BD_SQLite(this, "bdAlumno", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigoBuscar = (EditText)findViewById(R.id.txtCodigoBuscar);
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnListar = (Button)findViewById(R.id.btnListar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistar);

        //Registrar Alumno | Inicio
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = txtCodigo.getText().toString();
                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                String correo = txtCorreo.getText().toString();

                bd.abrir();
                bd.registrarAlumno(id, nombre, apellido, correo);
                bd.cerrar();

                Toast.makeText(getApplicationContext(), "Alumno registrado con éxito!", Toast.LENGTH_LONG).show();

                txtCodigo.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtCorreo.setText("");
                txtCodigoBuscar.requestFocus();

            }
        });
        //Registrar Alumno | Fin

        //Buscar Alumno | Inicio
        btnBuscar.setOnClickListener(new View.OnClickListener() {

            String codigo, nombre, apellido, correo;

            @Override
            public void onClick(View view) {



                bd.abrir();

                try{
                    Cursor cursor = bd.buscarAlumno(txtCodigoBuscar.getText().toString());

                    if (cursor.moveToFirst()){
                        codigo = cursor.getString(0);
                        nombre = cursor.getString(1);
                        apellido = cursor.getString(2);
                        correo = cursor.getString(3);
                    }

                    Toast.makeText(getApplicationContext(), "Alumno encontrado!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), BuscarAlumno.class);

                    i.putExtra("codigo", codigo);
                    i.putExtra("nombre", nombre);
                    i.putExtra("apellido", apellido);
                    i.putExtra("correo", correo);

                    startActivity(i);

                }finally {
                    bd.cerrar();
                }

            }
        });
        //Buscar Alumno | Fin

        //Listado de Alumnos | Inicio
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.abrir();
                Intent i = new Intent(getApplicationContext(), ListadoAlumnos.class);
                startActivity(i);
                bd.cerrar();

            }
        });
        //Listado de Alumnos | Fin
    }
}
