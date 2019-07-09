package com.example.ktask.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class Helper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?,
    private val DATABASE_VERSION: Int,
    private val DATABASE_NAME: String
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_PLACE_TABLE =
            ("CREATE_TABLE " + "place" + "(" + "COLUMN_ID" + "INTEGER PRIMARY KEY ," + "COLUMN_NAME" + "TEXT" + ")")
        db?.execSQL(CREATE_PLACE_TABLE)
        Log.e("table created", "" + CREATE_PLACE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}



