package com.herprogramacion.uml.otros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.uml.R;
import com.herprogramacion.uml.correo.EnviarCorreoActivity;


public class AyudaActivity extends Activity{
    private ImageView imvHelp;
    private TextView txvAyuda;
    private TextView txvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda_layout);
        initComponents();
    }
    private void initComponents(){

    }

    public void enviarCorreo(View view) {
        startActivity(new Intent(this, EnviarCorreoActivity.class));
    }
    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }
}
