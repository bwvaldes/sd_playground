package com.scubadeving.sd_playground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.bottom_bar
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.nav_view

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        nav_view.setupWithNavController(navController)
        configureBottomNavBarVisibility(navController)
    }

    private fun configureBottomNavBarVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_dashboard,
                R.id.navigation_logbook,
                R.id.navigation_explore,
                R.id.navigation_catalog -> {
                    nav_view.visibility = View.VISIBLE
                    bottom_bar.visibility = View.VISIBLE
                    fab.visibility = View.VISIBLE
                }
                else -> {
                    nav_view.visibility = View.GONE
                    bottom_bar.visibility = View.GONE
                    fab.visibility = View.GONE
                }
            }
        }
    }
}
