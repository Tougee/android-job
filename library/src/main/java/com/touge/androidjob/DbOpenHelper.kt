package com.touge.androidjob

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbOpenHelper(private val context: Context, private val name: String):
    SQLiteOpenHelper(context, name, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "job.db"
        const val DB_VERSION = 1


        class Columns {
            val ID = "id"
            val PRIORITY = "priority"
            val RETRY_COUNT = "retry_count"
            val REQUIRE_TYPE = "require_type"
            val GROUP_ID = "group_id"
        }
    }



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}