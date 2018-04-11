package com.example.joseluis.registraralunmo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import BDHELPER.alumnos;

/**
 * Created by JOSE LUIS on 4/04/2018.
 */

public class Adapter_alumno extends RecyclerView.Adapter<Adapter_alumno.viewholder> {
    private List<alumnos> listalumnos;
    private Context context;

    public Adapter_alumno(Context context ,List<alumnos> listalumnos) {
        this.listalumnos = listalumnos;
        this.context=context;
    }


    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alumno,parent,false);
        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        alumnos al=listalumnos.get(position);
        holder.nombre.setText(al.getNombre());
        holder.escuela.setText(al.getEscuela());
        holder.codigo.setText(String.valueOf(al.getCodigo()));
    }

    @Override
    public int getItemCount() {
        Log.w("##########", String.valueOf(listalumnos.size()));
        return listalumnos.size();
    }
    public class viewholder extends RecyclerView.ViewHolder
    {
        public TextView codigo,nombre,escuela;
        public RelativeLayout viewBackground ,viewForeground;
        public viewholder(View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.idnombre);
            escuela=itemView.findViewById(R.id.idescuela);
            codigo=itemView.findViewById(R.id.idcodigo);
            viewBackground=itemView.findViewById(R.id.view_background);
            viewForeground=itemView.findViewById(R.id.view_viewForeground);
        }
    }
    public void removeItem(int position)
    {
        listalumnos.remove(position);
        notifyItemRemoved(position);
    }
    //   public void restoreItem(alumnos alumnos,int position)
    //   {
    //     listalumnos.add(position,alumnos );
    //  notifyItemInserted(position);
    //  }
}
