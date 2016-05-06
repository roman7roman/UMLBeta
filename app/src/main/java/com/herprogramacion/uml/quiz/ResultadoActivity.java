package com.herprogramacion.uml.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.herprogramacion.uml.R;
import com.herprogramacion.uml.grafica.GraficaTopic;
import com.herprogramacion.uml.grafica.GraficaHelperDos;
import com.herprogramacion.uml.grafica.GraficoActivityDos;
import com.herprogramacion.uml.otros.CapitulosActivity;


public class ResultadoActivity extends Activity {

    GraficaHelperDos db= new GraficaHelperDos(this);
    GraficaTopic g= new GraficaTopic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
//get text view
        TextView t=(TextView)findViewById(R.id.textResult);
//get score
        Bundle b = getIntent().getExtras();////////-------------------------Resive la info de la base de datos
        int score= b.getInt("score");
//display score
        bar.setRating(score);
        switch (score) {
            case 1:

            case 2: t.setText("Lo siento, no has pasado satisfactoriamente el cuestionario");
                break;
            case 3:
                break;
            case 4:t.setText("Muy bien pero deberias realizar de nuevo el quiz para sacar una calificacion mas alta");
                break;
            case 5:
                t.setText("Eres asombroso, eres un Dios, has pasado satisfactoriamente el curso");
                g.setNombre("Capitulos del 1 al 5");
                g.setSigla("UML");
                g.setVotos(10);
                db.insertResult(g);
                break;
            default:

        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    public void playagain(View o) {
        Intent intent = new Intent(this, CapitulosActivity.class);
        startActivity(intent);
    }

    public void irGrafica(View view) {
        Intent intent = new Intent(this, GraficoActivityDos.class);
        startActivity(intent);

    }
    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }
}
