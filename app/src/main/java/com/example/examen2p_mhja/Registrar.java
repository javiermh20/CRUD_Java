package com.example.examen2p_mhja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
    EditText us, pas, nom;
    String gen, hab, esc;
    String[] valores = {"Licenciatura", "Maestria","Doctorado"};
    Spinner spinner1;
    CheckBox habJava, habKotlin, habPython;
    Button reg, can;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        us = (EditText) findViewById(R.id.RegUser);
        pas = (EditText) findViewById(R.id.RegPass);
        nom = (EditText) findViewById(R.id.RegNombre);
        reg = (Button) findViewById(R.id.btnRegRegistrar);
        can = (Button) findViewById(R.id.btnRegCancelar);
        dao = new UsuarioDAO(this);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        spinner1 =(Spinner)findViewById(R.id.RegSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, valores);
        spinner1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setGenero(validarG());
                u.setEscolaridad(spinner1.getSelectedItem().toString());
                hab = validarH();
                u.setHabilidades(hab);
                if(!u.isNull()){
                    Toast.makeText(this,"ERROR campos vacios", Toast.LENGTH_LONG).show();
                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this,"Registro Exitoso!!",Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Registrar.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                } else{
                    Toast.makeText(this,"Usuario ya registrado!",Toast.LENGTH_LONG).show();
                }

                Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.btnRegCancelar:
                Intent i = new Intent(Registrar.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
    String validarG(){
        String vGenero = "";
        RadioButton gen1 = (RadioButton)findViewById(R.id.RegRadioH);
        RadioButton gen2 = (RadioButton) findViewById(R.id.RegRadioM);
        if(!gen1.isChecked() && !gen2.isChecked()) {
            Toast.makeText(this, "ERROR debes elegir un genero",Toast.LENGTH_LONG).show();
        } else if(gen2.isChecked()){
            vGenero = "Mujer";
        } else {
            vGenero = "Hombre";
        }
        return vGenero;
    }

    String validarH(){
        String vHabilidad = "";
        habJava = (CheckBox)findViewById(R.id.RegJava);
        habKotlin = (CheckBox)findViewById(R.id.RegKotlin);
        habPython = (CheckBox)findViewById(R.id.RegPython);
        if(!habJava.isChecked() && !habKotlin.isChecked() &&!habPython.isChecked()){
            Toast.makeText(this,"ERROR debes elegir una habilidad", Toast.LENGTH_LONG).show();
        } else if(habJava.isChecked() && habKotlin.isChecked() && habPython.isChecked()){
            vHabilidad = "Java, Kotlin, Python";
        } else if(habJava.isChecked() && habKotlin.isChecked() &&!habPython.isChecked()){
            vHabilidad = "Java y Kotlin";
        } else if(habJava.isChecked() && !habKotlin.isChecked() && habPython.isChecked()){
            vHabilidad = "Java y Python";
        } else if(!habJava.isChecked() && habKotlin.isChecked() && habPython.isChecked()){
            vHabilidad = "Kotlin y Python";
        } else if(habJava.isChecked() && !habKotlin.isChecked() &&!habPython.isChecked()){
            vHabilidad = "Java";
        } else if(!habJava.isChecked() && habKotlin.isChecked() &&!habPython.isChecked()){
            vHabilidad = "Kotlin";
        } else if(!habJava.isChecked() && !habKotlin.isChecked() && habPython.isChecked()){
            vHabilidad = "Python";
        }
        return vHabilidad;
    }
}