package com.herprogramacion.uml.quiz;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.herprogramacion.uml.R;


public class FormularioInterno extends AppCompatActivity implements View.OnClickListener{
    private EditText edtUserName,edtPassword;
    private Button btnRegister;

    //Hace referencia al usuario creado en la base de datos
    private SQLiteDatabase db;
    private Usuario usuario;
    private DbHelper dbHelper;
    private UsuarioDAOImpl dao;
    public static int ID_USARIOS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initComponent();
    }

    private void initComponent() {
        edtUserName = (EditText)findViewById(R.id.username);
        edtPassword = (EditText)findViewById(R.id.password);
        btnRegister = (Button)findViewById(R.id.register);

        dbHelper= new DbHelper(this);
        db= dbHelper.getWritableDatabase();
        dao= new UsuarioDAOImpl();

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int error = 0;
        if (edtUserName.length()<4|edtPassword.length()<4){
            Toast.makeText(FormularioInterno.this, "El tamaño minimo de 4", Toast.LENGTH_SHORT).show();
            error++;
        }
        if (error==0){
            usuario = new Usuario(
                    0,
                    edtUserName.getText().toString(),
                    edtPassword.getText().toString()

            );

            if (usuario.getIdUsuario()==0){
                int[] dat=dbHelper.loginInternoDb(edtUserName.getText().toString(),edtPassword.getText().toString());
                dbHelper.addTopics(dat[1]);
                dao.agregar(usuario, db);
                ID_USARIOS=dat[1];
                Toast.makeText(FormularioInterno.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
