package com.aneke.peter.scientia.data

import android.content.Context
import android.content.SharedPreferences
import java.util.*
import java.util.concurrent.TimeUnit

private const val SHARED_PREF = "shared_pref"
private val timeThreshold = TimeUnit.MINUTES.toMillis(30) //30 minutes
private const val LAST_SYNC_TIME = "last_sync_time"

class PrefStore (private val context: Context) {

    private val sharedPref: SharedPreferences by lazy { context.getSharedPreferences(SHARED_PREF,
        Context.MODE_PRIVATE
    ) }

    private val editor: SharedPreferences.Editor = sharedPref.edit()

    var lateSyncTime : Long
    set(value) = editor.putLong(LAST_SYNC_TIME, value).apply()
    get() = sharedPref.getLong(LAST_SYNC_TIME, 0)

    fun shouldRefreshData() : Boolean{
        val diff = Calendar.getInstance().timeInMillis - lateSyncTime
        return diff > timeThreshold
    }


}