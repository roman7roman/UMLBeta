package com.herprogramacion.uml.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.herprogramacion.uml.R;
import com.herprogramacion.uml.login.ReadComments;



public class LoginInterno  extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName,edtPassword;
    private Button btnRegister;
    private Button btnLogin;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initComponent();

    }

    private void initComponent() {
        dbHelper=new DbHelper(this);
        edtUserName = (EditText)findViewById(R.id.username);
        edtPassword = (EditText)findViewById(R.id.password);
        btnRegister = (Button)findViewById(R.id.register);
        btnLogin = (Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //vista de login, donde ingresan si ya tienen cuenta
            case R.id.login:
                String nombr,passs;
                nombr=edtUserName.getText().toString();
                passs=edtPassword.getText().toString();
                int[] datos=dbHelper.loginInternoDb(nombr,passs);
                Log.e("Posicion 1", "onClick: "+datos[0]);
                Log.e("Posicion 2", "onClick: "+datos[1]);
                if (datos[0]==1){
                    startActivity(new Intent(LoginInterno.this, ReadComments.class));
                    finish();
                }else {
                    Toast.makeText(LoginInterno.this, "Contraseña o usuario incorrectos", Toast.LENGTH_SHORT).show();
                }
                break;
            //vista de registro, donde se ingresan los campos para registrar un nuevo usuario.
            case R.id.register:
                Intent i = new Intent(this, FormularioInterno.class);
                startActivity(i);
                break;
            default:
                break;
            }
        }
    }
