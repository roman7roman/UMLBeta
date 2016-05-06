package com.herprogramacion.uml.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.herprogramacion.uml.R;

import java.util.ArrayList;


public class Grafica extends AppCompatActivity {


    private RelativeLayout graficaLayout;
    private PieChart grafica;
    private float[] yData;
    private String[] xData;
    DbHelper dbHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica_layout);
        dbHelper = new DbHelper(this);


        int cfgh =
                dbHelper.getProgreso(FormularioInterno.ID_USARIOS);
        if (cfgh == 5) {
            cfgh = 100;

        } else if (cfgh == 4) {
            cfgh = 80;
        } else if (cfgh == 3) {
            cfgh = 60;
        } else if (cfgh == 2) {
            cfgh = 40;
        } else if (cfgh == 1) {
            cfgh = 20;
        } else {
            cfgh = 0;
        }
        int score = cfgh;

        final Bundle b = new Bundle();
        b.putInt("puntuacion", score);

        yData = new float[]{score, 100 - score};
        xData = new String[]{"Cursado", "Restante"};

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        graficaLayout = (RelativeLayout) findViewById(R.id.grafica_layout);
        grafica = new PieChart(this);


        graficaLayout.addView(grafica);
        graficaLayout.setBackgroundColor(Color.LTGRAY);

        grafica.setUsePercentValues(true);
        grafica.setDescription("Avance General del Curso");
        grafica.setDescriptionTextSize(15f);
        grafica.setDescriptionPosition(370f, 60f);

        grafica.setDrawHoleEnabled(true);
        grafica.setHoleRadius(50);
        grafica.setTransparentCircleRadius(1);
        grafica.setHoleColorTransparent(true);

        grafica.setRotationAngle(0);
        grafica.setRotationEnabled(true);


        grafica.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null) {
                    return;
                }

                Toast.makeText(Grafica.this, xData[e.getXIndex()] + " = " + e.getVal() + " % ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        Legend l = grafica.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }


    private void addData(){
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i=0; i<yData.length; i++){
            yVals1.add(new Entry(yData[i],i));
        }

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i=0; i<xData.length;i++){
            xVals.add(xData[i]);
        }

        PieDataSet dataSet = new PieDataSet(yVals1,"");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer>colores = new ArrayList<Integer>();

        for (int c: ColorTemplate.LIBERTY_COLORS){
            colores.add(c);
        }

        colores.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colores);

        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.RED);

        grafica.setData(data);
        grafica.highlightValues(null);
        grafica.invalidate();
    }
}
