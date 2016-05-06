package com.herprogramacion.uml.otros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.herprogramacion.uml.R;
import com.herprogramacion.uml.quiz.DbHelper;
import com.herprogramacion.uml.quiz.FormularioInterno;


public class CapitulosActivity extends Activity implements AdapterView.OnItemClickListener{
        private ListView lsvCapitulos;
    private DbHelper dbHelper;
    private String[] capitulos = {
            "Introducción a UML",
            "Casos de Uso",
            "Diagramas de Actividad",
            "Diagramas de Estado",
            "Diagrama de Interaccion"};
    private Bundle bundle;
    private String capitulo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captitulos_layout);
        initComponents();
    }
    private void initComponents(){
        dbHelper=new DbHelper(this);
        lsvCapitulos = (ListView)findViewById(R.id.lsv_capitulos);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_layout, R.id.txv_item, capitulos);
        lsvCapitulos.setAdapter(adapter);
        lsvCapitulos.setOnItemClickListener(this);
        bundle = new Bundle();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        bundle = new Bundle();
        bundle.putInt(getString(R.string.position), position);

        switch (position){
            case 0:
                if (dbHelper.statusTema(FormularioInterno.ID_USARIOS,1)){
                    startActivity(new Intent(CapitulosActivity.this, TabsActivity.class).putExtras(bundle));
                }else {
                    Toast.makeText(CapitulosActivity.this, "Tema 1 bloqueado", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (dbHelper.statusTema(FormularioInterno.ID_USARIOS,2)){
                    startActivity(new Intent(CapitulosActivity.this, TabsActivity.class).putExtras(bundle));
                }else {
                    Toast.makeText(CapitulosActivity.this, "Tema 2 bloqueado", Toast.LENGTH_SHORT).show();

                }
                break;
            case 2:
                if (dbHelper.statusTema(FormularioInterno.ID_USARIOS,3)){
                    startActivity(new Intent(CapitulosActivity.this, TabsActivity.class).putExtras(bundle));
                }else {
                    Toast.makeText(CapitulosActivity.this, "Tema 3 bloqueado", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (dbHelper.statusTema(FormularioInterno.ID_USARIOS,4)){
                    startActivity(new Intent(CapitulosActivity.this, TabsActivity.class).putExtras(bundle));
                }else {
                    Toast.makeText(CapitulosActivity.this, "Tema 4 bloqueado", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (dbHelper.statusTema(FormularioInterno.ID_USARIOS,5)){
                    startActivity(new Intent(CapitulosActivity.this, TabsActivity.class).putExtras(bundle));
                }else {
                    Toast.makeText(CapitulosActivity.this, "Tema 5 bloqueado", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }
}
