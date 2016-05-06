package com.herprogramacion.uml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GaleriaDosActivity extends Activity implements View.OnClickListener {

    ImageView foto;
    TextView tv;
    int[] fotoId = {R.drawable.utng, R.drawable.cafe, R.drawable.estrella,
            R.drawable.entrada,R.drawable.descarga1};
    String[] textos = {"UTNG", "UTNG", "UTNG", "UTNG", "UTNG"};
    int i = 0;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_uno);

        Button anterior = (Button) findViewById(R.id.button1);
        Button siguiente = (Button) findViewById(R.id.button2);
        anterior.setOnClickListener(this);
        siguiente.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.textView1);
        foto = (ImageView) findViewById(R.id.imageView1);
        total = fotoId.length;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == R.id.button2) {
            i++;
            if(i == total){
                i = 0;
            }
        }

        if(id == R.id.button1) {
            i--;
            if(i == -1){
                i = total -1;
            }
        }
        foto.setImageResource(fotoId[i]);
        tv.setText(textos[i]);

    }

}
