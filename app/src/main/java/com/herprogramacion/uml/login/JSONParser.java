package com.herprogramacion.uml.login;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

//Clases importadas, necesarias para hacer la solicitud de conexion a internet



//JSON SIRVE PARA ENVIAR Y RECIVIR DATOS, es un subconjunto de  la notación
// literal de objetos de JavaScript que no requiere el uso de XML
public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor vacio
    public JSONParser() {

    }


    public JSONObject getJSONFromUrl(final String url) {

        // Hace peticion a HTTP
        try {
            // Contructor de cliente y servidor HTTP.
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // Ejecutar la solicitu POST y almacenar la respuesta localmente.
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // Extraer los datos de la respuesta.
            HttpEntity httpEntity = httpResponse.getEntity();
            //Abrir un InputStream con el contenido de los datos .
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //Crear un BufferedReader para analizar a través de la flujoEntrada .
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            // Declarar un constructor de cadena para ayudar con el análisis sintáctico .
            StringBuilder sb = new StringBuilder();
            // Declarar una cadena para almacenar los datos de objetos JSON en forma de cadena .
            String line = null;

            // Contruir el  string nulo.
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // Cerrar el flujo de entrada.
            is.close();
            //Convertir los datos del constructor cadena en una cadena real .
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // Se trata de analizar la cadena  del objeto JSON
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Retorna el objeto JSON
        return jObj;

    }


    public JSONObject makeHttpRequest(String url, String method,
                                      List params) {

        // Haciendo la Petición HTTP
        try {

            //comprobar si hay método de la petición
            if(method == "POST"){
                // Metodo de peticion POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(method == "GET"){
                // Metodo de la posicion get
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        //Intentar analizar la cadena a un objeto JSON
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Retorna el string json
        return jObj;

    }
}