package com.touge.androidjob

import android.content.ContentResolver
import com.touge.androidjob.provider.SqlHelper
import com.touge.androidjob.scheduler.Scheduler
import java.util.concurrent.atomic.AtomicBoolean

class JobConsumerRunnable(config: Config) : Runnable {

    private val contentResolver: ContentResolver = config.context.contentResolver
    private val scheduler: Scheduler = config.scheduler

    private val isRunning = AtomicBoolean(false)

    private val runningGroups = arrayListOf<String>()
    private val runningIds = arrayListOf<String>()

    override fun run() {
        while (isRunning.get()) {
            var job: Job? = null
            try {
                val constraint = SqlHelper.createConstraint(runningGroups, runningIds)
                // TODO query job by constraint
            } catch (e: Exception) {

            }
        }
    }
}