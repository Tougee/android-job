package com.touge.androidjob

import java.io.Serializable

class Params(
    val priority: Int,
    val retryCount: Int,
    val requireType: RequireType,
    val groupId: String?
): Serializable {

    companion object {
        const val DEFAULT_RETRY_LIMIT = 10

    }

    class Builder {
        var priority = 0
        var retryCount = DEFAULT_RETRY_LIMIT
        var requireType = RequireType.LOCAL
        var groupId: String? = null

        fun build() = Params(priority, retryCount, requireType, groupId)
    }
}