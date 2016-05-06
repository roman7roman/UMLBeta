package com.herprogramacion.uml.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.herprogramacion.uml.R;
import com.herprogramacion.uml.otros.ActividadPrincipal;


public class ReadComments extends Activity  {

    Button btnCursoStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // cuenta que el uso read_comments.xml en lugar de nuestra single_post.xml
        //layout de la vista
        setContentView(R.layout.read_comments);
        btnCursoStar = (Button) findViewById(R.id.btn_inicio);


    }

    //onClick declarado en el boton inicio.
    public void inicio(View view) {
        Intent intent = new Intent(this, ActividadPrincipal.class);
        Button button = (Button) findViewById(R.id.btn_inicio);
        startActivity(intent);
    }

    //metodo para finalizar la ventana y no aparezca al momento de retroceder en la aplicacion.
    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }

}
