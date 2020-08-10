package com.scubadeving.sd_playground

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        visibilityNavElements(navController)
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.certDetailFragment,
                R.id.diveSiteDetailFragment,
                R.id.wildlifeFragment,
                R.id.settingsFragment -> {
                    nav_view.visibility = View.GONE
                    bottomBar.visibility = View.GONE
                    fab.visibility = View.GONE
                }
                else -> {
                    nav_view.visibility = View.VISIBLE
                    bottomBar.visibility = View.VISIBLE
                    fab.visibility = View.VISIBLE
                }
            }
        }
    }
}