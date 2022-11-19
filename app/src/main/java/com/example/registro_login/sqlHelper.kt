package com.example.registro_login

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

class sqlHelper(context: Context, name: String, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, "Regsitro.db", null, 1) {

        override fun onCreate(db: SQLiteDatabase){

            val ordenCreacion = "CREATE TABLE registro (_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, correo TEXT, contrasena TEXT)"
            db.execSQL(ordenCreacion)
        }

    override fun onUpgrade(db: SQLiteDatabase, olVersion: Int, newVersion: Int) {

    }

    fun add(usuario: String, correo:String,contrasena:String){
        val datos = ContentValues()

        datos.put("usuario",usuario)
        datos.put("correo",correo)
        datos.put("contrasena",contrasena)

        val db = this.writableDatabase
        db.insert("registro",null,datos)
        db.close()
    }
}