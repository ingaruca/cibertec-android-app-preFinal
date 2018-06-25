package com.dev.softsky.prefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarAlumno extends AppCompatActivity {

    EditText txtConsultaCodigo, txtConsultaNombre, txtConsultaApellido, txtConsultaCorreo;
    Button btnGrabar, btnEliminar;

    //Instanciar la bd
    BD_SQLite bd = new BD_SQLite(this, "bdAlumno", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_alumno);

        txtConsultaCodigo = (EditText)findViewById(R.id.txtConsultaCodigo);
        txtConsultaNombre = (EditText)findViewById(R.id.txtConsultaNombre);
        txtConsultaApellido = (EditText)findViewById(R.id.txtConsultaApellido);
        txtConsultaCorreo = (EditText)findViewById(R.id.txtConsultaCorreo);

        btnGrabar = (Button)findViewById(R.id.btnGrabar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        Bundle recupera = getIntent().getExtras();

        if (recupera != null){
            txtConsultaCodigo.setText(recupera.getString("codigo"));
            txtConsultaNombre.setText(recupera.getString("nombre"));
            txtConsultaApellido.setText(recupera.getString("apellido"));
            txtConsultaCorreo.setText(recupera.getString("correo"));
        }

        //Editar Alumno | Inicio
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    bd.abrir();

                    String id = txtConsultaCodigo.getText().toString();
                    String nombre = txtConsultaNombre.getText().toString();
                    String apellido = txtConsultaApellido.getText().toString();
                    String correo = txtConsultaCorreo.getText().toString();

                    bd.editarAlumno(id, nombre, apellido, correo);

                    Toast.makeText(getApplicationContext(), "Registro grabado!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), ListadoAlumnos.class);
                    startActivity(i);

                }finally {
                    bd.cerrar();
                }

            }
        });
        //Editar Alumno | Fin

        //Eliminar Alumno | Inicio
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.abrir();
                bd.eliminarAlumno(txtConsultaCodigo.getText().toString());
                bd.cerrar();

                Toast.makeText(getApplicationContext(), "Registro eliminado!", Toast.LENGTH_LONG).show();

                finish();

                Intent i = new Intent(getApplicationContext(), ListadoAlumnos.class);
                startActivity(i);

            }
        });
        //Eliminar Alumno | Fin

    }
}
