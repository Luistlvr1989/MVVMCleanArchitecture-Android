package com.globant.cleanarchitecture.extensions.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDestination

fun NavDestination.getDestinationIdFromAction(@IdRes actionId: Int) = getAction(actionId)?.destinationId