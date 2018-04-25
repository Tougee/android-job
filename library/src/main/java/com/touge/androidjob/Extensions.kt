package com.touge.androidjob

fun StringBuilder.appendPlaceHolders(count: Int): StringBuilder {
    if (count <= 0) return this

    append("?")
    for (i in 1..count) {
        append(",?")
    }
    return this
}