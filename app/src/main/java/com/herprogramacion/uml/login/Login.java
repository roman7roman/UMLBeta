package com.herprogramacion.uml.login;
import com.herprogramacion.uml.R;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements OnClickListener {

    //Elementos declarados, despues inicializados, pertenecen a la vista.
    private EditText user, pass;
    private Button mSubmit, mRegister;

    private ProgressDialog pDialog;

    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();


    // si trabajan de manera local "localhost" :
    // En windows tienen que ir, run CMD > ipconfig
    // buscar su IP
    // y poner de la siguiente manera
    // "http://xxx.xxx.x.x:1234/cas/login.php";

    private static final String LOGIN_URL = "http://aplicacionajax.esy.es/cas/login.php";

    // La respuesta del JSON es
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    //Declaramos la interfaz en xml.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // campos de entrada para ingresar
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        // Botones para ingresar
        mSubmit = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);

        // asignacion de eventos a cada uno de los botones.
        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }

    //metodo en donde se presionan los botones y se dirigen a diferentes vistas.
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //vista de login, donde ingresan si ya tienen cuenta
            case R.id.login:
                new AttemptLogin().execute();
                break;
            //vista de registro, donde se ingresan los campos para registrar un nuevo usuario.
            case R.id.register:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
//ventana que se muestra cuando ingresa un usuario.
    class AttemptLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            //mensaje que proyecta
            pDialog.setMessage("Autentificando Usuario...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            try {
                // Parametros de contruccion.
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                //obtener los detalles del producto al hacer la solicitud HTTP
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                        params);

                // comprobar el registro para la respuesta JSON
                Log.d("Login attempt", json.toString());

                // json ventana de ingreso excitoso.
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Ingreso Exitoso!", json.toString());
                    // guardar la informacion del usuario
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(Login.this);
                    Editor edit = sp.edit();
                    edit.putString("username", username);
                    edit.commit();
                    //se inicia ventana previa antes de iniciar la aplicacion.
                    Intent i = new Intent(Login.this, ReadComments.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Fallo el registro!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // cerrar el diálogo una vez que el producto elimina
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
}