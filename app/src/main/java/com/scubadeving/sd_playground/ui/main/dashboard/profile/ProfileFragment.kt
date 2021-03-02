package com.scubadeving.sd_playground.ui.main.dashboard.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentProfileBinding
import com.scubadeving.sd_playground.ui.adapters.viewpager.ProfileViewPagerAdapter

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return FragmentProfileBinding.inflate(inflater, container, false).apply {
            configureProfileToolbar()
            configureProfileViewPager()
            profileViewModel.currentDiver.observe(viewLifecycleOwner, Observer { diver = it })
        }.root
    }

    private fun FragmentProfileBinding.configureProfileToolbar() {
        profileToolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            setOnMenuItemClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
                true
            }

            var isToolbarShown = false

            // scroll change listener begins at Y = 0 when image is fully collapsed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                profilePager.setOnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        profileAppbar.isActivated = shouldShowToolbar

                        // Show the  name if toolbar is shown
                        profileToolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            }
        }
    }

    private fun FragmentProfileBinding.configureProfileViewPager() {
        profilePager.apply {
            adapter = ProfileViewPagerAdapter(requireParentFragment())
            TabLayoutMediator(profileTabLayout, this) { tab, position ->
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
