package com.fastcontech.weatherapp.domain

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class Util(private val context: Context, private val activity: Activity) {

    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        } else {
            println("Already permitted!!")
        }
    }

    fun onRequestPermissionsResult(grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            println("Location Permission Granted")
        } else {
            println("Location Permission Denied")
        }
    }

    fun dateTimeConverter(epoch: Long): LocalDate? {
        return Instant.ofEpochMilli(epoch)
            .atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
