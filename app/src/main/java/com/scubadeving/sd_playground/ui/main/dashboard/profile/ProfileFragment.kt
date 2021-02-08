package com.scubadeving.sd_playground.ui.main.dashboard.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.ProfileViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Edit Profile", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_edit))
        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.profile_toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        toolbar.setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
            true
        }

        var isToolbarShown = false

        val pager: ViewPager2 = root.findViewById(R.id.profile_pager)
        val appbar: AppBarLayout = root.findViewById(R.id.profile_appbar)
        val toolbarLayout: CollapsingToolbarLayout = root.findViewById(R.id.toolbarLayout)
        // scroll change listener begins at Y = 0 when image is fully collapsed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pager.setOnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profile_pager.apply {
            adapter = ProfileViewPagerAdapter(requireParentFragment())
            TabLayoutMediator(profile_tab_layout, this) { tab, position ->
                tab.text = when (position) {
                    PROFILE_TAB_CERTS -> getString(R.string.profile_tab_certs)
                    PROFILE_TAB_STATS -> getString(R.string.profile_tab_stats)
                    PROFILE_TAB_GEAR -> getString(R.string.profile_tab_gear)
                    PROFILE_TAB_ABOUT -> getString(R.string.profile_tab_about)
                    else -> getString(R.string.profile_tab_certs)
                }
            }.attach()
        }
    }

    companion object {
        private const val PROFILE_TAB_CERTS = 0
        private const val PROFILE_TAB_STATS = 1
        private const val PROFILE_TAB_GEAR = 2
        private const val PROFILE_TAB_ABOUT = 3
    }
}