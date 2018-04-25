package com.touge.androidjob.di

import com.touge.androidjob.Job

interface DependencyInjector {
    fun inject(job: Job)
}