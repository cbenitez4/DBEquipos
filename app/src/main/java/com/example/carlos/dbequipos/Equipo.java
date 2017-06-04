package com.example.carlos.dbequipos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Equipo {

    private String foto;
    private String nombre;
    private String liga;
    private String dt;
    private String mj;
    private String estadio;
    private String apodo;


    public Equipo(String foto, String nombre, String liga, String dt, String mj, String estadio, String apodo) {
        this.foto = foto;
        this.nombre = nombre;
        this.liga = liga;
        this.dt = dt;
        this.mj = mj;
        this.estadio = estadio;
        this.apodo = apodo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void guardar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        EquiposSQLiteOpenHelper  aux = new EquiposSQLiteOpenHelper(contexto);
        db = aux.getWritableDatabase();

        //insertar
        sql = "INSERT INTO Equipos values('"

                +this.getFoto()+"','"
                +this.getNombre()+"','"
                +this.getLiga()+"','"
                +this.getDt()+"','"
                +this.getMj()+"','"
                +this.getEstadio()+"','"
                +this.getApodo()+"')";

        db.execSQL(sql);

        db.close();

    }

    }
    //Terminadagit