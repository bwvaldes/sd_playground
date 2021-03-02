package com.scubadeving.sd_playground

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.scubadeving.sd_playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            configureBottomNavBar(navController)
        }
    }

    private fun ActivityMainBinding.configureBottomNavBar(navController: NavController) {
        navView.apply {
            setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                visibility = when (destination.id) {
                    R.id.navigation_dashboard,
                    R.id.navigation_logbook,
                    R.id.navigation_explore,
                    R.id.navigation_catalog -> VISIBLE
                    else -> GONE
                }
            }
        }
    }
}
