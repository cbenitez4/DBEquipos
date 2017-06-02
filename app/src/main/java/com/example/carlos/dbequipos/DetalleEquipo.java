package com.example.carlos.dbequipos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleEquipo extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Equipo equipo;
    private String nombre,liga,fotodetalle;
    private Bundle b;
    private Intent i;
    private ImageView foto;
    private TextView dtdetalle,mjdetalle,estadiodetalle,apododetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String equipoId = i.getStringExtra("equipo_id");
        if (TextUtils.isEmpty(equipoId)) {
            finish();
        }
        equipo = buscarEquipo(equipoId);
        if (equipo == null) {
            finish();
            return;
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        foto = (ImageView) findViewById(R.id.fotoPersona);
        dtdetalle = (TextView) findViewById(R.id.txtdtdetalle);
        mjdetalle = (TextView) findViewById(R.id.txtmjdetalle);
        estadiodetalle = (TextView) findViewById(R.id.txtestadiodetalle);
        apododetalle = (TextView) findViewById(R.id.txtapododetalle);

        setValores();

    }
    private void setValores() {
        collapsingToolbarLayout.setTitle(equipo.getNombre() + " " + equipo.getLiga());

        Picasso.with(getApplicationContext())
                .load(equipo.getFoto())
                .into(foto);

        dtdetalle.setText(equipo.getDt());
        mjdetalle.setText(equipo.getMj());
        estadiodetalle.setText(equipo.getEstadio());
        apododetalle.setText(equipo.getApodo());
    }


    public Equipo buscarEquipo(String EquipoId) {
        Equipo p = null;
        SQLiteDatabase db;
        String foto, nombre, liga, dt,mj,estadio,apodo;

        EquiposSQLiteOpenHelper psoh = new EquiposSQLiteOpenHelper(getApplicationContext());
        db = psoh.getReadableDatabase();
        String sql = "Select foto, nombre,liga,dt,mj,estadio,apodo from Equipos" +
                " Where nombre LIKE '" + EquipoId + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            foto = c.getString(0);
            nombre = c.getString(1);
            liga = c.getString(2);
            dt = c.getString(3);

            mj = c.getString(4);
            estadio = c.getString(5);
            apodo = c.getString(6);

            p = new Equipo(foto, nombre, liga, dt,mj,estadio,apodo);
        }
        c.close();
        db.close();

        return p;
    }
}


