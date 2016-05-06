package com.herprogramacion.uml.otros;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.herprogramacion.uml.R;

import java.util.List;

public class Linterna extends Activity {

    private Button btEncenderLinterna;
    private Button btApagarLinterna;

    private Camera dispCamara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linterna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btEncenderLinterna = (Button) findViewById(R.id.btEncenderLinterna);
        btEncenderLinterna.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                processOnClick();
            }
        });

        btApagarLinterna = (Button) findViewById(R.id.btApagarLinterna);
        btApagarLinterna.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                processOffClick();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            dispCamara = Camera.open();
        }
        catch( Exception e )
        {
            Toast.makeText(getApplicationContext(),
                    "No se ha podido acceder a la cámara",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause()
    {
        if( dispCamara != null )
        {
            dispCamara.release();
            dispCamara = null;
        }
        super.onPause();
    }

    private void processOffClick(){
        if( dispCamara != null ){
            Parameters parametrosCamara = dispCamara.getParameters();
            parametrosCamara.setFlashMode(Parameters.FLASH_MODE_OFF);
            dispCamara.setParameters(parametrosCamara);
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "No se ha podido acceder al Flash de la cámara",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void processOnClick()
    {
        Toast.makeText(getApplicationContext(),
                "Accediendo a la cámara", Toast.LENGTH_LONG).show();

        if( dispCamara != null )
        {
            Toast.makeText(getApplicationContext(),
                    "Cámara encontrada", Toast.LENGTH_LONG).show();
            Parameters parametrosCamara = dispCamara.getParameters();

            //Get supported flash modes
            List modosFlash = parametrosCamara.getSupportedFlashModes ();


            if (modosFlash != null &&
                    modosFlash.contains(Camera.Parameters.FLASH_MODE_TORCH))
            {
                //Set the flash parameter to use the torch
                parametrosCamara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                try
                {
                    dispCamara.setParameters(parametrosCamara);
                    dispCamara.startPreview();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error al activar la linterna",
                            Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "El dispositivo no tiene el modo de Flash Linterna",
                        Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "No se ha podido acceder al Flash de la cámara",
                    Toast.LENGTH_LONG).show();
        }
    }
}


