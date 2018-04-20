package com.touge.androidjob

import android.content.Context
import java.util.regex.Pattern

data class Config(
    val context: Context,
    var id: String,
    var maxConsumerCount: Int,
    var minConsumerCount: Int
) {
    companion object {
        const val DEFAULT_ID = "default_job_manager"
        const val MAX_CONSUMER_COUNT = 5
        const val MIN_CONSUMER_COUNT = 0
    }

    private constructor(builder: Builder) : this(builder.context, builder.id, builder.maxConsumerCount, builder.minConsumerCount)

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

        fun build(context: Context) = Config(this)
    }
}