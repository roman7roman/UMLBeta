package com.herprogramacion.uml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuGaleriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_galeria);
        findViewById(R.id.btn_galeria1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGaleriaActivity.this, GaleriaUnoActivity.class));
            }
        });
        findViewById(R.id.btn_galeria2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGaleriaActivity.this, GaleriaDosActivity.class));
            }
        });

        findViewById(R.id.btn_galeria3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGaleriaActivity.this, GaleriaTresActivity.class));
            }
        });




    }
}
