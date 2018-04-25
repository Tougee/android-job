package com.touge.androidjob

import android.content.ContentResolver
import com.touge.androidjob.provider.JobContact
import java.util.concurrent.atomic.AtomicBoolean

class JobAdderRunnable(private val config: Config, private val addJobQueue: AddJobQueue) : Runnable {

    private val contentResolver: ContentResolver = config.context.contentResolver

    private val isRunning = AtomicBoolean(false)

    override fun run() {
        while (isRunning.get()) {
            var job: Job? = null
            try {
                job = addJobQueue.poll()
                if (job != null) {
                    job.next = null
                    // TODO check if running
                    contentResolver.insert(JobContact.Jobs.URI_TABLE, job.contentValue())

                    config.dependencyInjector?.inject(job)

                    job.onAdded()
                    //TODO schedule
                }
            } catch (e: Exception) {
                job?.onCancel()
            }
        }
    }
}