package com.touge.androidjob

import java.util.concurrent.Executors

class JobManager(private val config: Config) {

    private val executor = Executors.newFixedThreadPool(2)
    private val addJobQueue = AddJobQueue()

    fun addJob(job: Job) {
        addJobQueue.offer(job)
    }


}