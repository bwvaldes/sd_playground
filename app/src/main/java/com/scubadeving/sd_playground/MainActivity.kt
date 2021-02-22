package com.scubadeving.sd_playground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
                R.id.logbookEntryFragment,
                R.id.settingsFragment,
                R.id.qrCodeFragment,
                R.id.exploreDetailsFilteredFragment,
                R.id.notificationsFragment,
                R.id.certificationScanFragment,
                R.id.inboxFragment,
                R.id.chatFragment,
                R.id.chatDetailFragment,
                R.id.weatherDetailFragment,
                R.id.profileFragment -> {
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