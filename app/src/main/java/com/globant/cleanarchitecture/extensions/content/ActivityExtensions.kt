package com.globant.cleanarchitecture.extensions.content

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Activity.goToActivity(intent: Intent) {
    finish()
    startActivity(intent)
}

fun AppCompatActivity.findNavController(@IdRes id: Int): NavController = with(supportFragmentManager) {
    val navHostFragment = findFragmentById(id) as NavHostFragment
    return navHostFragment.navController
}

fun AppCompatActivity.hasArgs(): Boolean = intent.extras != null