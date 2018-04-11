package com.example.joseluis.registraralunmo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import BDHELPER.SQLite;
import BDHELPER.alumnos;

public class Activity_mostrar extends AppCompatActivity {
    private ArrayList<BDHELPER.alumnos> alumnos=new ArrayList<>();
    private ListView lista;
    private int alumnoSeleccionado=-1;
    private Object mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista=(ListView)findViewById(R.id.LVmostrar);
        llenarlista();
        onClick();
    }
    //actualiza la lista cuando actualizas
    public void onResume()
    {
        super.onResume();
        alumnos.removeAll(alumnos);
        llenarlista();
    }
    // --->
    //ver mas opciCones
    public void onClick()
    {
        //   Rview.;
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                alumnoSeleccionado=position;
                mActionMode=Activity_mostrar.this.startActionMode(amc);
                view.setSelected(true);
                return true;
            }
        });
    }
    private ActionMode.Callback amc=new ActionMode.Callback()
    {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.EliminarItem){
                eliminarAlumno();
                actionMode.finish();
            }else if (menuItem.getItemId() == R.id.EditarItem){
                alumnos alum= alumnos.get(alumnoSeleccionado);
                Intent in = new Intent(Activity_mostrar.this,Activity_editar.class);
                in.putExtra("id",alum.getIdalumno());
                startActivity(in);
                actionMode.finish();
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };
    public void llenarlista()
    {
        SQLite bd=new SQLite(Activity_mostrar.this,"alumno",null,1);
        if (bd!=null)
        {
            SQLiteDatabase db=bd.getReadableDatabase();
            Cursor c =db.rawQuery("SELECT * FROM alumno",null);
            if (c.moveToFirst())
            {
                do {
                    alumnos.add(new alumnos(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3)));
                }while (c.moveToNext());
            }
        }
        String[] arreglo =new  String[alumnos.size()];
        for (int i=0;i<arreglo.length;i++)
        {
            arreglo[i]=alumnos.get(i).getNombre();
        }
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(Activity_mostrar.this,android.R.layout.simple_list_item_1,arreglo);
        lista.setAdapter(adaptador);
    }
    public void  eliminarAlumno()
    {
        SQLite bd=new SQLite(Activity_mostrar.this,"alumno",null,1);
        if (bd!=null)
        {
            SQLiteDatabase db=bd.getReadableDatabase();
            BDHELPER.alumnos alum = alumnos.get(alumnoSeleccionado);
            long response= db.delete("alumno","idalumno="+alum.getIdalumno(),null);
            if (response>0)
            {
                Toast.makeText(Activity_mostrar.this,"Eliminado con eito",Toast.LENGTH_LONG).show();
                alumnos.removeAll(alumnos);
                llenarlista();
            }else{
                Toast.makeText(Activity_mostrar.this,"Fallo la Eliminacion",Toast.LENGTH_LONG).show();
            }
        }
    }
}
