package com.touge.androidjob

import java.util.concurrent.Executors

class JobManager(config: Config) {

    private val executor = Executors.newFixedThreadPool(2)

    companion object {
        class AddRunnable(val job: Job): Runnable {
            override fun run() {

            }
        }
    }
}