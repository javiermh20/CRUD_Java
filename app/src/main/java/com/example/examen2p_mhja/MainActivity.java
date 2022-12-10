package com.example.examen2p_mhja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText user, pass;
Button btnEntrar, btnRegistrar;
UsuarioDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.User);
        pass = (EditText)findViewById(R.id.Pass);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao = new UsuarioDAO(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this, "ERROR campos vacios", Toast.LENGTH_SHORT).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux = dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos Correctos!!",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(MainActivity.this,Mostrar.class);
                    i2.putExtra("id",ux.getUsuarioID());
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this,"Usuario y/o Password icorrectos!!",Toast.LENGTH_LONG).show();
                }
                    break;
            case R.id.btnRegistrar:
                Intent i = new Intent(MainActivity.this, Registrar.class);
                startActivity(i);
                break;
        }
    }
}