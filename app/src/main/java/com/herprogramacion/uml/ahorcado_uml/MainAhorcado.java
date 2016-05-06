package com.herprogramacion.uml.ahorcado_uml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.herprogramacion.uml.R;


public class MainAhorcado extends AppCompatActivity implements View.OnClickListener{

    Button btnEmpezar,btnPuntos,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ahorcado_principal);

        btnEmpezar = (Button) findViewById(R.id.btn_empezar);
        btnPuntos = (Button) findViewById(R.id.btn_puntuar);
        btnSalir = (Button) findViewById(R.id.btn_salir);

        btnEmpezar.setOnClickListener(this);
        btnPuntos.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        findViewById(R.id.btn_galeria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAhorcado.this, com.herprogramacion.uml.MenuGaleriaActivity.class));
            }
        });
        findViewById(R.id.btn_lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAhorcado.this, com.herprogramacion.uml.otros.Linterna.class));
            }
        });
        findViewById(R.id.btn_radio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAhorcado.this, com.herprogramacion.uml.otros.Radio_Layaout.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnEmpezar){
            Intent i = new Intent(this, JuegoActivity.class);
            startActivity(i);
        }else if (v == btnPuntos){

        }else if(v == btnSalir){
            finish();
        }
    }
}
