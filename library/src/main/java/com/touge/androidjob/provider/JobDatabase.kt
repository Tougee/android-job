package com.touge.androidjob.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.touge.androidjob.provider.JobContact.JobColumns.GROUP_ID
import com.touge.androidjob.provider.JobContact.JobColumns.ID
import com.touge.androidjob.provider.JobContact.JobColumns.PRIORITY
import com.touge.androidjob.provider.JobContact.JobColumns.REQUIRE_TYPE
import com.touge.androidjob.provider.JobContact.JobColumns.RETRY_COUNT

class JobDatabase(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "job.db"
        const val DB_VERSION = 1

        fun deleteDatabase(context: Context) {
            context.deleteDatabase(DB_NAME)
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $DB_NAME (" +
            "$ID TEXT NOT NULL PRIMARY KEY, " +
            "$PRIORITY INTEGER NOT NULL DEFAULT 0, " +
            "$RETRY_COUNT INTEGER NOT NULL DEFAULT 0, " +
            "$REQUIRE_TYPE INTEGER NOT NULL DEFAULT 0, " +
            "$GROUP_ID TEXT, " +
            "UNIQUE ($ID) ON CONFLICT REPLACE")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}