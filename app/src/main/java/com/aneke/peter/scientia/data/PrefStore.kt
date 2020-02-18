package com.aneke.peter.scientia.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*

private const val SHARED_PREF = "shared_pref"
private const val timeThreshold = 1800000 //30 minutes in millis
private const val LAST_SYNC_TIME = "last_sync_time"

class PrefStore (private val context: Context) {

    private val sharedPref: SharedPreferences by lazy { context.getSharedPreferences(SHARED_PREF,
        Context.MODE_PRIVATE
    ) }

    private val editor: SharedPreferences.Editor = sharedPref.edit()

    var lateSyncTime : Long
    set(value) = editor.putLong(LAST_SYNC_TIME, value).apply()
    get() = sharedPref.getLong(LAST_SYNC_TIME, 0)


    val apiKey = "14926f6cf0033ec20601efd142210575"

    fun shouldRefreshData() : Boolean{
        val diff = Calendar.getInstance().timeInMillis - lateSyncTime
        return diff >= timeThreshold
    }


}