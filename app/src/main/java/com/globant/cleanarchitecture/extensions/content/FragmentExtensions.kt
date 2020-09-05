package com.globant.cleanarchitecture.extensions.content

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.globant.cleanarchitecture.extensions.navigation.getDestinationIdFromAction

fun Fragment.navigate(@IdRes actionId: Int) {
    if (!isAlreadyAtDestination(actionId)) {
        findNavController().navigate(actionId)
    }
}

fun Fragment.navigate(directions: NavDirections) {
    if (!isAlreadyAtDestination(directions.actionId)) {
        findNavController().navigate(directions)
    }
}

private fun Fragment.isAlreadyAtDestination(@IdRes actionId: Int): Boolean {
    val previousDestinationId = previousDestination()?.getDestinationIdFromAction(actionId)
    val currentDestinationId = currentDestination()?.id
    return previousDestinationId == currentDestinationId
}

fun Fragment.currentDestination() = findNavController().currentDestination

fun Fragment.previousDestination() = findNavController().previousBackStackEntry?.destination

fun Fragment.goBack() = findNavController().popBackStack()