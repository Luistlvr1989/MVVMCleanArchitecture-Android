package com.globant.cleanarchitecture.utils

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

object TimberUtils {
    fun init(isLogging: Boolean) {
        val reportingTree = if (isLogging) {
            object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String =
                    super.createStackElementTag(element) + ':' + element.lineNumber
            }
        } else CrashReportingTree()

        Timber.plant(reportingTree)
    }

    @SuppressLint("LogNotTimber")
    private class CrashReportingTree : Timber.Tree() {
        companion object {
            private const val MAX_LOG_LENGTH = 4000
        }

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return
            }

            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }

            var i = 0
            val length = message.length
            while (i < length) {
                var newLine = message.indexOf('\n', i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = newLine.coerceAtMost(i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < newLine)
                i++
            }
        }
    }
}