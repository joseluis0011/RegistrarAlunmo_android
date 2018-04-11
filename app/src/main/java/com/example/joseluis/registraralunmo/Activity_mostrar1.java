package com.example.joseluis.registraralunmo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import BDHELPER.SQLite;
import BDHELPER.alumnos;

public class Activity_mostrar1 extends AppCompatActivity {
    private ArrayList<BDHELPER.alumnos> alumnos=new ArrayList<>();
    private ListView lista;
    private int alumnoSeleccionado=-1;
    private Object mActionMode;

    private RecyclerView Rview;
    private Adapter_alumno AdapterA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar1);
        lista=(ListView)findViewById(R.id.LVmostrar1);

        Rview=findViewById(R.id.Recicle);

        llenarlista();
        onClick();

        AdapterA = new Adapter_alumno(this,alumnos);
        RecyclerView.LayoutManager manager =new LinearLayoutManager(getApplicationContext());
        Rview.setLayoutManager(manager);
        Rview.setItemAnimator(new DefaultItemAnimator());
        Rview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Rview.setAdapter(AdapterA);

    }
    //ver mas opciCones
    public void onClick()
    {
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                alumnoSeleccionado=position;
                mActionMode=Activity_mostrar1.this.startActionMode(amc);
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
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };
    public void llenarlista()
    {
        SQLite bd=new SQLite(Activity_mostrar1.this,"alumno",null,1);
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
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(Activity_mostrar1.this,android.R.layout.simple_list_item_1,arreglo);
        lista.setAdapter(adaptador);
    }
}
