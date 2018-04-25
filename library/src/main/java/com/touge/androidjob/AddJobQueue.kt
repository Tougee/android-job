package com.touge.androidjob

class AddJobQueue {
    private var toe: Job? = null
    private var tail: Job? = null

    private val lock = java.lang.Object()

    @Synchronized
    fun poll(): Job? {
        val result = toe
        if (result != null) {
            toe = result.next
            if (tail == result) {
                tail = null
            }
        } else {
            lock.wait()
        }
        return result
    }

    @Synchronized
    fun offer(job: Job) {
        if (tail == null) {
            tail = job
            toe = job
        } else {
            tail!!.next = job
            tail = job
        }
        lock.notifyAll()
    }
}