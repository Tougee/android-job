package com.touge.androidjob

import android.content.Context
import com.touge.androidjob.di.DependencyInjector
import com.touge.androidjob.scheduler.DefaultScheduler
import com.touge.androidjob.scheduler.Scheduler
import java.util.regex.Pattern

data class Config(
    val context: Context,
    var id: String,
    var maxConsumerCount: Int,
    var minConsumerCount: Int,
    var dependencyInjector: DependencyInjector?,
    var scheduler: Scheduler
) {
    companion object {
        const val DEFAULT_ID = "default_job_manager"
        const val MAX_CONSUMER_COUNT = 5
        const val MIN_CONSUMER_COUNT = 0
    }

    private constructor(builder: Builder) : this(builder.context, builder.id, builder.maxConsumerCount,
        builder.minConsumerCount, builder.dependencyInjector, builder.scheduler)

    class Builder(val context: Context) {
        private val idRegex: Pattern = Pattern.compile("^([A-Za-z]|[0-9]|_|-)+$")

        var id: String = DEFAULT_ID
            set(value) {
                if (!idRegex.matcher(value).matches()) {
                    throw IllegalArgumentException("id cannot be null or empty and can only include"
                        + " alphanumeric characters, - or _ .")
                }
            }
        var maxConsumerCount: Int = MAX_CONSUMER_COUNT
        var minConsumerCount: Int = MIN_CONSUMER_COUNT
        var dependencyInjector: DependencyInjector? = null
        var scheduler: Scheduler = DefaultScheduler()

        fun build(context: Context) = Config(this)
    }
}