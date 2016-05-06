package com.herprogramacion.uml.login;
import com.herprogramacion.uml.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Register extends Activity implements OnClickListener{
    //Elementos declarados, despues inicializados, pertenecen a la vista.
    private EditText user, pass;
    private Button  mRegister;

    // Barra de progreso
    private ProgressDialog pDialog;

    // clase que analisa el json
    JSONParser jsonParser = new JSONParser();

    //si lo trabajan de manera local en xxx.xxx.x.x va su ip local
    // private static final String REGISTER_URL = "http://xxx.xxx.x.x:1234/cas/register.php";

    //poner la url del sitio en donde creaste tu host:
    private static final String REGISTER_URL = "http://aplicacionajax.esy.es/cas/register.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //nombre del layout
        setContentView(R.layout.register);

        // campos de entrada para ingresar
        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);

        // Botones para ingresar
        mRegister = (Button)findViewById(R.id.register);
        // asignacion de eventos a cada uno de los botones.
        mRegister.setOnClickListener(this);

    }

    //metodo en donde se presionan los botones y se dirigen a diferentes vistas.
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        new CreateUser().execute();
    }
    //Clase interna
    class CreateUser extends AsyncTask<String, String, String> {

        //mensaje que se muestra cuando se esta creando un usuario
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creando Usuario...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            //parametros de contruccion
            String username = user.getText().toString();
            String password = pass.getText().toString();
            try {
                // Contruccion de los parametros
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");

                //La publicación de los datos del usuario a la escritura
                JSONObject json = jsonParser.makeHttpRequest(
                        REGISTER_URL, "POST", params);

                // respuesta JSON completa
                Log.d("Validando Registro", json.toString());

                //JSON elemento de éxito
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Usuario Creado!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                    //JSON elemento de incorrecto
                }else{
                    Log.d("Fallo el Registro!", json.getString(TAG_MESSAGE));
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
            if (file_url != null){
                Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}