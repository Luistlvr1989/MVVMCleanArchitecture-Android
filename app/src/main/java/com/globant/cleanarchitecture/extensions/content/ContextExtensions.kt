package com.globant.cleanarchitecture.extensions.content

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasPermissions(permissions: Array<String>): Boolean {
    permissions.forEach { permission ->
        if (!hasPermission(permission)) {
            return false
        }
    }

    return true
}

fun Context.hasPermission(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
