package com.globant.cleanarchitecture.extensions.navigation

import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph

fun NavController.findNavGraph(@NavigationRes id: Int): NavGraph = navInflater.inflate(id)

val NavController.currentLabel: String?
    get() = currentDestination?.label.toString()
