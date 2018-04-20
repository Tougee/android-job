package com.touge.androidjob

import java.io.Serializable
import java.util.UUID

abstract class Job(val params: Params): Serializable {

    private val id: String by lazy { UUID.randomUUID().toString() }

    abstract fun onAdded()

    abstract fun onRun()

    abstract fun shouldRetry(): Boolean

    abstract fun onCancel()
}