package com.herprogramacion.uml.quiz;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.herprogramacion.uml.R;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class PreguntaActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    //Declaracion de las variables que se utilizaran.
    List<Pregunta> quesList;
    int score= +1;
    int qid=0;
    Pregunta currentQ;
    TextView txtQuestion, times;
    RadioButton rda, rdb, rdc;
    Button butNext;
    private Bundle valoresRecibidos;


    //Declaramos la interfaz en xml.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_main_quiz);
        dbHelper= new DbHelper(this);

        valoresRecibidos=getIntent().getExtras();
        switch (valoresRecibidos.getInt("posicionTema")){
            case 0:
                quesList=dbHelper.getAllQuestion(0);
                break;
            case 1:
                quesList=dbHelper.getAllQuestion(1);//Trae las preguntas de 1 a 5
                break;
            case 2:
                quesList=dbHelper.getAllQuestion(2);//Trae las preguntas de 5 a 10
                break;
            case 3:
                quesList=dbHelper.getAllQuestion(3);//Trae las preguntas de 10 a 15
                break;
            case 4:
                quesList=dbHelper.getAllQuestion(4);//Trae las preguntas de 20 a 25
                break;
            default:
                break;
        }
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);

        times = (TextView) findViewById(R.id.timers);
        //método que fijará las cosas en el juego
        setQuestionView();
        //Texto en la vista
        times.setText("00:02:00");
        // El tiempo de 60 segundos, en un intervalo de 1 segundo son  (1000 milliseconds)
        final  CounterClass timer = new CounterClass(60000, 1000);
        timer.start();


        setQuestionView();//Rellena la informacion de cada pregunta

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);

                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getANSWER()+" "+ answer.getText());
                if (currentQ.getANSWER().equals(answer.getText())){
                    score++;
                    Log.d("score", "Your Score" + score);
                }
                switch (valoresRecibidos.getInt("posicionTema")) {
                }
                //Numero de preguntas que mostrara al momento de seleccionar cada opcion del listView
                if (qid < 5) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                //Si las preguntas superan el numero
                    switch (valoresRecibidos.getInt("posicionTema")){
                        case 0:
                                dbHelper.insertaCalifTemas(score,FormularioInterno.ID_USARIOS,1);
                            break;
                        case 1:
                            dbHelper.insertaCalifTemas(score,FormularioInterno.ID_USARIOS,2);

                            break;
                        case 2:
                            dbHelper.insertaCalifTemas(score,FormularioInterno.ID_USARIOS,3);

                            break;
                        case 3:
                            dbHelper.insertaCalifTemas(score,FormularioInterno.ID_USARIOS,4);

                            break;
                        case 4:
                            dbHelper.insertaCalifTemas(score,FormularioInterno.ID_USARIOS,5);

                            break;
                        default:
                            Log.e("preguntaActivity", "onClick: No entro we " );
                            break;
                    }
                    Intent intent = new Intent(PreguntaActivity.this, ResultadoActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Tu puntuacion
                    intent.putExtras(b); //Pone tu puntuacion en cada intento
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    //Elementos necesarios para el conteo
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onFinish() {
            times.setText("El tiempo acabo");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView(){
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }


}
