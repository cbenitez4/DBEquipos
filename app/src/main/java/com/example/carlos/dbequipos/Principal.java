package com.example.carlos.dbequipos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorEquipo.OnEquipoClickListener {
    private RecyclerView listado;
    private ArrayList<Equipo> equipos;
    private AdaptadorEquipo adapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listado = (RecyclerView) findViewById(R.id.lstOpciones);

        equipos = Datos.traerEquipos(getApplicationContext());
        adapter = new AdaptadorEquipo(equipos,this);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);
    }

    public void agregar(View v){
        finish();
        Intent i =new Intent(Principal.this, AgregarEquipo.class);
        startActivity(i);
    }

    @Override
    public void onEquipoClick(Equipo p) {
        Intent i = new Intent(Principal.this, DetalleEquipo.class);
        i.putExtra("equipo_id", p.getNombre());
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
