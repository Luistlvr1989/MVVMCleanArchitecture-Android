package com.globant.cleanarchitecture.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.extensions.content.findNavController
import com.globant.cleanarchitecture.extensions.navigation.findNavGraph

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareUI()
    }

    private fun prepareUI() {
        navController = findNavController(R.id.navHostFragment)
        navGraph = navController.findNavGraph(R.navigation.nav_graph)
    }
}