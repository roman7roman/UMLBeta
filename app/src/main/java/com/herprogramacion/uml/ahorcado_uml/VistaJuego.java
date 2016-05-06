package com.herprogramacion.uml.ahorcado_uml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import com.herprogramacion.uml.R;

/**
 * Created by Mauricio on 05/04/2016.
 */
public class VistaJuego extends View {

    private Context context;
    private Bitmap imagen;
    private Marcador marcador;

    public VistaJuego(Context context, String palabra) {
        super(context);

        this.context = context;
        this.marcador = new Marcador(context,palabra);
        this.imagen = BitmapFactory.decodeResource(context.getResources(), R.drawable.patibulo);
    }

    public void introducirLetra(char letra){
        boolean finalizado =this.marcador.comprobarPalabra(letra);
        if (finalizado){
            Toast.makeText(this.context,"Eres un CRACK",Toast.LENGTH_SHORT).show();
        }else if(this.marcador.ahorcado()){
                Toast.makeText(this.context, "Fracasado",Toast.LENGTH_SHORT).show();

            }

        invalidate();
        }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(this.imagen, canvas.getWidth() / 2 - this.imagen.getWidth() / 2, canvas.getHeight() / 2 - this.imagen.getHeight() / 2, new Paint());
        this.marcador.draw(canvas);
    }


}
