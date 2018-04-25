package com.touge.androidjob.provider

import com.touge.androidjob.appendPlaceHolders

class SqlHelper {

    companion object {
        fun createConstraint(runningGroups: ArrayList<String>, runningIds: ArrayList<String>): Pair<String, Array<String>> {
            val sb = StringBuilder()
            var argsCount = 0
            if (runningGroups.isNotEmpty()) {
                sb.append(" AND (")
                    .append(JobContact.JobColumns.GROUP_ID)
                    .append(" IS NULL OR ")
                    .append(JobContact.JobColumns.GROUP_ID)
                    .append(" NOT IN(")
                    .appendPlaceHolders(runningGroups.size)
                    .append("))")
                argsCount += runningGroups.size
            }
            if (runningIds.isNotEmpty()) {
                sb.append(" AND (")
                    .append(JobContact.JobColumns.ID)
                    .append(" IS NULL OR ")
                    .append(JobContact.JobColumns.ID)
                    .append(" NOT IN(")
                    .appendPlaceHolders(runningIds.size)
                    .append("))")
                argsCount += runningIds.size
            }
            val args = arrayOf(argsCount.toString())
            return Pair(sb.toString(), args)
        }
    }
}