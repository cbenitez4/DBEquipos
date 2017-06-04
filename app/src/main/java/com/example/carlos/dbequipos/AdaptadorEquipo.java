package com.example.carlos.dbequipos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Carlos on 29/05/2017.
 */

public class AdaptadorEquipo extends RecyclerView.Adapter<AdaptadorEquipo.EquipoViewHolder> {
    private ArrayList<Equipo> equipos;
    private OnEquipoClickListener clickListener;

    public AdaptadorEquipo(ArrayList<Equipo> equipos, OnEquipoClickListener clickListener){
        this.equipos=equipos;
        this.clickListener=clickListener;
    }

    @Override
    public AdaptadorEquipo.EquipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_equipo,parent,false);
        return new EquipoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorEquipo.EquipoViewHolder holder, int position) {
            final Equipo p = equipos.get(position);
        holder.foto.setImageResource(Integer.parseInt(p.getFoto()));

        holder.nombre.setText(p.getNombre());
        holder.liga.setText(p.getLiga());
        holder.dt.setText(p.getDt());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEquipoClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView liga;
        private TextView nombre;
        private TextView dt;
        private View view;

        public EquipoViewHolder(View itemView) {
            super(itemView);
            view= itemView;
            foto = (ImageView)itemView.findViewById(R.id.foto);
            nombre = (TextView) itemView.findViewById(R.id.txtNombreP);
            liga = (TextView)itemView.findViewById(R.id.txtLigaP);
            dt = (TextView)itemView.findViewById(R.id.txtDtP);
        }
    }

    public interface OnEquipoClickListener{
        void onEquipoClick(Equipo p);
    }
}
