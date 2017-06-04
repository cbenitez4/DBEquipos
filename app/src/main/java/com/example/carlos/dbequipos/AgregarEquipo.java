package com.example.carlos.dbequipos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class AgregarEquipo extends AppCompatActivity {
    private EditText cajaLiga,cajaDt,cajaNombre,cajaMj,cajaEstadio,cajaApodo;
    private boolean guardado;
    private TextInputLayout icajaLiga,icajaDt,icajaNombre,icajaMj,icajaEstadio,icajaApodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_equipo);

        cajaNombre= (EditText)findViewById(R.id.txtNombre);
        cajaLiga = (EditText)findViewById(R.id.txtLiga);
        cajaDt = (EditText)findViewById(R.id.txtDt);
        cajaMj = (EditText) findViewById(R.id.txtMj);
        cajaEstadio = (EditText) findViewById(R.id.txtEstadio);
        cajaApodo = (EditText) findViewById(R.id.txtApodo);

        icajaNombre = (TextInputLayout)findViewById(R.id.NombrePersona);
        icajaLiga = (TextInputLayout) findViewById(R.id.LigaPersona);
        icajaDt = (TextInputLayout)findViewById(R.id.DtPersona);
        icajaMj = (TextInputLayout) findViewById(R.id.MjPersona);
        icajaEstadio = (TextInputLayout) findViewById(R.id.EstadioPersona);
        icajaApodo = (TextInputLayout) findViewById(R.id.ApodoPersona);
        guardado = false;

        cajaNombre.addTextChangedListener(new TextWatcherPersonalizado(icajaNombre,getResources().getString(R.string.mensaje_error_nombre)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });
        cajaLiga.addTextChangedListener(new TextWatcherPersonalizado(icajaLiga,getResources().getString(R.string.mensaje_error_liga)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });

        cajaDt.addTextChangedListener(new TextWatcherPersonalizado(icajaDt,getResources().getString(R.string.mensaje_error_dt)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

        cajaMj.addTextChangedListener(new TextWatcherPersonalizado(icajaMj,getResources().getString(R.string.mensaje_error_mj)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });
        cajaEstadio.addTextChangedListener(new TextWatcherPersonalizado(icajaEstadio,getResources().getString(R.string.mensaje_error_estadio)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });

        cajaApodo.addTextChangedListener(new TextWatcherPersonalizado(icajaApodo,getResources().getString(R.string.mensaje_error_apodo)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });
    }

    public int fotoAleatoria(){
        int fotos[] = {R.drawable.images2,R.drawable.images3,R.drawable.images};
        int numero = (int)(Math.random() * 3);
        return fotos[numero];
    }

    public void guardar(View v)  {
        String foto,nombre,liga,dt,mj,estadio,apodo;
        Equipo p;

        if(validarTodo()){


            foto = String.valueOf(fotoAleatoria());
            nombre = cajaLiga.getText().toString();
            liga = cajaNombre.getText().toString();
            dt=cajaDt.getText().toString();
            mj = cajaMj.getText().toString();
            estadio = cajaEstadio.getText().toString();
            apodo=cajaApodo.getText().toString();

            p = new Equipo(foto,liga,nombre,dt,mj,estadio,apodo);
            p.guardar(getApplicationContext());

            InputMethodManager imp =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imp.hideSoftInputFromWindow(cajaNombre.getWindowToken(),0);
            Snackbar.make(v,getResources().getString(R.string.mensaje_exitoso_guardar),Snackbar.LENGTH_SHORT).show();
            guardado= true;
            limpiar();

        }
    }

    public void limpia(View v){
        limpiar();
    }

    public void limpiar(){
        cajaLiga.setText("");
        cajaNombre.setText("");
        cajaDt.setText("");
        cajaMj.setText("");
        cajaEstadio.setText("");
        cajaApodo.setText("");

        cajaNombre.requestFocus();

        guardado = false;
    }
    public boolean validarTodo(){
        if(cajaNombre.getText().toString().isEmpty()){
            icajaNombre.setError(getResources().getString(R.string.mensaje_error_nombre));
            cajaNombre.requestFocus();
            return false;
        }
        if(cajaLiga.getText().toString().isEmpty()){
            icajaLiga.setError(getResources().getString(R.string.mensaje_error_liga));
            cajaLiga.requestFocus();
            return false;
        }

        if(cajaDt.getText().toString().isEmpty()){
            icajaDt.setError(getResources().getString(R.string.mensaje_error_dt));
            cajaDt.requestFocus();
            return false;
        }
        if(cajaMj.getText().toString().isEmpty()){
            icajaMj.setError(getResources().getString(R.string.mensaje_error_mj));
            cajaMj.requestFocus();
            return false;
        }
        if(cajaEstadio.getText().toString().isEmpty()){
            icajaEstadio.setError(getResources().getString(R.string.mensaje_error_estadio));
            cajaEstadio.requestFocus();
            return false;
        }

        if(cajaApodo.getText().toString().isEmpty()){
            icajaApodo.setError(getResources().getString(R.string.mensaje_error_apodo));
            cajaApodo.requestFocus();
            return false;
        }


        return true;
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarEquipo.this,Principal.class);
        startActivity(i);
    }
}
