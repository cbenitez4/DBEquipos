package com.example.carlos.dbequipos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class Datos {
    public static ArrayList<Equipo> traerEquipos(Context contexto){
        ArrayList<Equipo> equipos = new ArrayList<>();

        //Declarar variables
        SQLiteDatabase db;
        String sql, foto, liga, nombre, dt , mj, estadio, apodo;
        Equipo p;
        //Abrir conexión de lectura
        EquiposSQLiteOpenHelper aux = new EquiposSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Equipos";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor
        if(c.moveToFirst()){
            do{
                foto=c.getString(0);
                nombre =c.getString(1);
                liga = c.getString(2);
                dt = c.getString(3);
                mj =c.getString(4);
                estadio = c.getString(5);
                apodo = c.getString(6);

                p = new Equipo(foto,nombre,liga,dt,mj,estadio,apodo);
                equipos.add(p);

            }while (c.moveToNext());
        }

        db.close();

        return equipos;

    }

    public static Equipo buscarEquipo(Context contexto, String nom){


        //Declarar variables
        SQLiteDatabase db;
        String sql, foto,nombre, liga,  dt, mj, estadio, apodo;
        Equipo p=null;
        //Abrir conexión de lectura
        EquiposSQLiteOpenHelper aux = new EquiposSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Equipos where nombre ='"+nom+"'";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor
        if(c.moveToFirst()){

            foto=c.getString(0);
            nombre =c.getString(1);
            liga = c.getString(2);
            dt = c.getString(3);
            mj =c.getString(4);
            estadio = c.getString(5);
            apodo = c.getString(6);

            p = new Equipo(foto,nombre,liga,dt,mj,estadio,apodo);
        }

        db.close();
        return p;
    }

}
