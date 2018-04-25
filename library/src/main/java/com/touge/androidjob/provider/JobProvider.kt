package com.touge.androidjob.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import com.touge.androidjob.provider.JobDatabase.Companion.DB_NAME

class JobProvider : ContentProvider() {
    companion object {
        const val JOB = 100
        const val JOB_ID = 101

        val uriMatcher = buildUriMatcher()

        private fun buildUriMatcher(): UriMatcher {
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            val authority = JobContact.CONTENT_AUTHORITY
            uriMatcher.addURI(authority, "job", JOB)
            uriMatcher.addURI(authority, "job/#", JOB_ID)
            return uriMatcher
        }
    }

    private lateinit var jobDatabase: JobDatabase

    override fun onCreate(): Boolean {
        jobDatabase = JobDatabase(context)
        return true
    }

    override fun getType(uri: Uri?): String {
        val match = uriMatcher.match(uri)
        return when (match) {
            JOB -> JobContact.Jobs.CONTENT_TYPE
            JOB_ID -> JobContact.Jobs.CONTENT_TYPE_ITEM
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        val db = jobDatabase.writableDatabase
        val match = uriMatcher.match(uri)
        when (match) {
            JOB -> {
                val row = db.insertOrThrow(DB_NAME, null, values)
                return JobContact.Jobs.buildJobUri(row.toString())
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val db = jobDatabase.writableDatabase
        val match = uriMatcher.match(uri)
        val queryBuilder = SQLiteQueryBuilder().apply { tables = DB_NAME }
        when (match) {
            JOB -> {
                // TODO
            }
            JOB_ID -> {
                val id = JobContact.Jobs.getJobId(uri)
                queryBuilder.appendWhere(JobContact.JobColumns.ID + "=" + id)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = jobDatabase.writableDatabase
        val match = uriMatcher.match(uri)
        var selectionCriteria: String? = selection
        when (match) {
            JOB -> {
                // TODO
            }
            JOB_ID -> {
                val id = JobContact.Jobs.getJobId(uri)
                selectionCriteria = JobContact.JobColumns.ID + "=" + id +
                    if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return db.update(DB_NAME, values, selectionCriteria, selectionArgs)
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = jobDatabase.writableDatabase
        val match = uriMatcher.match(uri)
        when (match) {
            JOB -> {
                // TODO
            }
            JOB_ID -> {
                val id = JobContact.Jobs.getJobId(uri)
                val selectionCriteria = JobContact.JobColumns.ID + "=" + id +
                    if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""
                return db.delete(DB_NAME, selectionCriteria, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return 0
    }

    fun deleteDatabase() {
        jobDatabase.close()
        JobDatabase.deleteDatabase(context)
        jobDatabase = JobDatabase(context)
    }
}