package com.touge.androidjob

import android.content.ContentValues
import com.touge.androidjob.provider.JobContact
import java.io.Serializable
import java.util.UUID

abstract class Job(private val params: Params) : Serializable {

    private val id: String by lazy { UUID.randomUUID().toString() }
    private var priority = params.priority
    private var retryCount = params.retryCount
    private var requireType = params.requireType
    private var groupId = params.groupId

    @Transient
    var next: Job? = null

    abstract fun onAdded()

    abstract fun onRun()

    abstract fun shouldRetry(): Boolean

    abstract fun onCancel()

    fun contentValue() = ContentValues().apply {
        put(JobContact.JobColumns.ID, id)
        put(JobContact.JobColumns.PRIORITY, priority)
        put(JobContact.JobColumns.RETRY_COUNT, retryCount)
        put(JobContact.JobColumns.REQUIRE_TYPE, requireType.ordinal)
        put(JobContact.JobColumns.GROUP_ID, groupId)
    }
}