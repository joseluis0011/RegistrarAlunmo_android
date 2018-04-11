package com.example.joseluis.registraralunmo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import BDHELPER.SQLite;

public class Activity_editar extends AppCompatActivity {
    private int alumnoEditar;
    private EditText codigo,nombre,escuela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Bundle extras = this.getIntent().getExtras();
        if (extras!=null)
        {
            alumnoEditar = extras.getInt("id");
        }
        nombre=(EditText) findViewById(R.id.ETeditarnombre);
        escuela=(EditText)findViewById(R.id.ETeditarescuela);
        codigo=(EditText)findViewById(R.id.ETeditarcodigo);

        reflejarCampos();
    }
    public void reflejarCampos()
    {
        SQLite bd=new SQLite(Activity_editar.this,"alumno",null,1);
        if (bd!=null)
        {
            SQLiteDatabase db= bd.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM alumno WHERE idalumno = "+alumnoEditar,null);
            try {
                if (c.moveToNext())
                {
                    nombre.setText(c.getString(1));
                    escuela.setText(c.getString(2));
                    codigo.setText(c.getString(3));
                }
            }finally {

            }
        }
    }
    public void editar(View view)
    {
        SQLite bd=new SQLite(Activity_editar.this,"alumno",null,1);
        if (bd!=null)
        {
            SQLiteDatabase db= bd.getReadableDatabase();
            ContentValues val=new ContentValues();
            val.put("nombre",nombre.getText().toString());
            val.put("escuela",escuela.getText().toString());
            val.put("codigo",Integer.parseInt(codigo.getText().toString()));
            long response  = db.update("alumno",val,"idalumno="+alumnoEditar,null);
            if (response>0)
            {
                Toast.makeText(Activity_editar.this,"Editado con Exito",Toast.LENGTH_LONG).show();
                nombre.setText("");
                escuela.setText("");
                codigo.setText("");
            }else{
                Toast.makeText(Activity_editar.this,"Ocurio un error",Toast.LENGTH_LONG).show();
            }
        }
    }
}
