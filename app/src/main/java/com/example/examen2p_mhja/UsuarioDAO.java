package com.example.examen2p_mhja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;
import java.util.ArrayList;

public class UsuarioDAO {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement,usuario text,pass text, nombre text, genero text, escolaridad text, habilidades text)";

    public UsuarioDAO(Context C){
        this.c = C;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if(buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("usuario",u.getUsuario());
            cv.put("pass",u.getPassword());
            cv.put("nombre",u.getNombre());
            cv.put("genero",u.getGenero());
            cv.put("escolaridad",u.getEscolaridad());
            cv.put("habilidades",u.getHabilidades());
            return (sql.insert("usuario",null,cv)>0);
        }else {
            return false;
        }
    }

    public int buscar(String u){
        int i=0;
        lista = selectUsuario();
        for(Usuario us:lista){
            if(us.getUsuario().equals(u)){
                i++;
            }
        }
        return i;
    }

    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario",null);
        if(cr!=null && cr.moveToFirst()){
            do{
                Usuario u = new Usuario();
                u.setUsuarioID(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setGenero(cr.getString(4));
                u.setEscolaridad(cr.getString(5));
                u.setHabilidades(cr.getString(6));
            }while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int i=0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    i++;
                }
            }while(cr.moveToNext());
        }
        return i;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuario();
        for(Usuario us: lista){
            if(us.getUsuario().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsuario();
        for(Usuario us: lista){
            if(us.getUsuarioID()==id){
                return us;
            }
        }
        return null;
    }
}
