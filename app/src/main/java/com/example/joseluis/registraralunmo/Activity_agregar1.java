package com.example.joseluis.registraralunmo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import BDHELPER.SQLite;

public class Activity_agregar1 extends AppCompatActivity {
    private EditText codigo, nombre;
    Spinner spinner;
    Button btnAgregar;
    String escuelaSeleccionada = "";//valor del Array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar1);
        codigo = (EditText) findViewById(R.id.ETcodigo);
        nombre = (EditText) findViewById(R.id.ETnombre);
        spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String[] valores = {"Sistemas", "Arquitectura", "Civil", "Ambiental", "Alimentos"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //    Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                escuelaSeleccionada = valores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });
    }
    public void agregar1(View view) {
        if (ComprobarCampos()) {
            //   Toast.makeText(Activity_agregar1.this,"No hay campos vacios",Toast.LENGTH_LONG).show();
            String nom, escu;
            int codi;
            nom = nombre.getText().toString();
            escu = escuelaSeleccionada;
            codi = Integer.parseInt(codigo.getText().toString());

            SQLite bd = new SQLite(Activity_agregar1.this, "alumno", null, 1);
            if (bd != null) {
                SQLiteDatabase db = bd.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre", nom);
                con.put("escuela", escu);
                con.put("codigo", codi);
                long insertado = db.insert("alumno", null, con);
                if (insertado > 0) {
                    Toast.makeText(Activity_agregar1.this, "Insertado con Exito", Toast.LENGTH_LONG).show();
                    nombre.setText("");
//                    escuela.setText("");
                    codigo.setText("");
                } else {
                    Toast.makeText(Activity_agregar1.this, "Error al Insertar", Toast.LENGTH_LONG).show();
                }
            }

        } else {
            Toast.makeText(Activity_agregar1.this, "hay campos vacios", Toast.LENGTH_LONG).show();
        }
    }
    public boolean ComprobarCampos() {
        if (nombre.getText().toString().isEmpty() || escuelaSeleccionada.isEmpty() || codigo.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    public void mostrar1(View view) {
        Intent activity_mostrar = new Intent(Activity_agregar1.this, Activity_mostrar1.class);
        startActivity(activity_mostrar);
    }
}
