package com.herprogramacion.uml.otros;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.uml.R;
import com.herprogramacion.uml.quiz.PreguntaActivity;



public class TabsActivity extends Activity implements View.OnClickListener{

    private TextView txvInformacion;
    private TextView txvForo;
    private TextView txvExamen;
    private ImageView imvQuiz;
    private String informacion;
    private Bundle bundle;
    private Bundle valoresRecibidosTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caracteristicas_layout);
        initComponentes();
    }

    private void initComponentes(){
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        Resources resources = getResources();

        TabHost.TabSpec spec = tabHost.newTabSpec("Información");
        spec.setContent(R.id.tab_informacion);
        spec.setIndicator("Información", resources.getDrawable(android.R.drawable.ic_menu_info_details));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Examen");
        spec.setContent(R.id.tab_examen);
        spec.setIndicator("Examen", resources.getDrawable(android.R.drawable.ic_menu_help));
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);


        txvInformacion = (TextView)findViewById(R.id.txv_informacion);
        txvExamen = (TextView)findViewById(R.id.txv_examen);
        imvQuiz = (ImageView)findViewById(R.id.imv_quiz);
        bundle = new Bundle();


        valoresRecibidosTab = getIntent().getExtras();

        switch (valoresRecibidosTab.getInt("posicion")){
            case 0:
                informacion = getString(R.string.introduccion_uml);
                break;
            case 1:
                informacion = getString(R.string.casos_uso);
                break;
            case 2:
                informacion = getString(R.string.diagrama_actividad);
                break;
            case 3:
                informacion = getString(R.string.diagrama_estado);
                break;
            case 4:
                informacion = getString(R.string.diagrama_interaccion);
                break;
        }


        txvInformacion.setText(informacion);
        imvQuiz.setOnClickListener(this);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.pestaña_pulsada) + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_quiz){
            bundle.putInt("posicionTema", valoresRecibidosTab.getInt("posicion"));
            startActivity(new Intent(this, PreguntaActivity.class).putExtras(bundle));
            }
        }
    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }

    }

