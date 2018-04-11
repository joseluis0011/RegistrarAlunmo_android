package com.example.joseluis.registraralunmo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import BDHELPER.SQLite;

public class Activity_agregar extends AppCompatActivity {
    private EditText codigo,nombre,escuela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        codigo=(EditText)findViewById(R.id.ETcodigo);
        nombre=(EditText)findViewById(R.id.ETnombre);
        escuela=(EditText)findViewById(R.id.ETescuela);
    }
    public void agregar(View view)
    {
        if (ComprobarCampos())
        {
            //   Toast.makeText(Activity_agregar.this,"No hay campos vacios",Toast.LENGTH_LONG).show();
            String nom,escu;
            int codi;
            nom=nombre.getText().toString();
            escu=escuela.getText().toString();
            codi=Integer.parseInt(codigo.getText().toString());

            SQLite bd=new SQLite(Activity_agregar.this,"alumno",null,1);
            if (bd!=null)
            {
                SQLiteDatabase db =bd.getWritableDatabase();
                ContentValues con =new ContentValues();
                con.put("nombre",nom);
                con.put("escuela",escu);
                con.put("codigo",codi);
                long insertado=db.insert("alumno",null,con);
                if (insertado>0)
                {
                    Toast.makeText(Activity_agregar.this,"Insertado con Exito",Toast.LENGTH_LONG).show();
                    nombre.setText("");
                    escuela.setText("");
                    codigo.setText("");
                }else{
                    Toast.makeText(Activity_agregar.this,"Error al Insertar",Toast.LENGTH_LONG).show();
                }
            }

        }else {
            Toast.makeText(Activity_agregar.this,"hay campos vacios",Toast.LENGTH_LONG).show();
        }
    }
    public boolean ComprobarCampos()
    {
        if (nombre.getText().toString().isEmpty() || escuela.getText().toString().isEmpty() || codigo.getText().toString().isEmpty())
        {
            return false;
        }else{
            return true;
        }
    }
    public void mostrar(View view)
    {
        Intent activity_mostrar=new Intent(Activity_agregar.this,Activity_mostrar.class);
        startActivity(activity_mostrar);
    }
}
