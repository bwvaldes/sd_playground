package com.scubadeving.sd_playground.ui.main.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.DiveCenter
import com.scubadeving.sd_playground.data.model.InboxNotification
import com.scubadeving.sd_playground.data.model.certification.CatalogCertification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.model.gear.Gear
import com.scubadeving.sd_playground.data.model.gear.GearProfile
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentDashboardBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationAdapter
import com.scubadeving.sd_playground.utils.TAG_FIRESTORE
import com.scubadeving.sd_playground.utils.TAG_POJO
import kotlinx.android.synthetic.main.activity_main.fab

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardViewModelFactory: DashboardViewModelFactory
    private lateinit var diverRepository: DiverRepository
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        diverRepository = DiverRepository(FirebaseFirestore.getInstance())
        dashboardViewModelFactory = DashboardViewModelFactory(diverRepository)
        dashboardViewModel = ViewModelProvider(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)

        return FragmentDashboardBinding.inflate(inflater, container, false).apply {
            activity?.fab?.setOnClickListener {
                Toast.makeText(activity, "Search Dashboard", Toast.LENGTH_SHORT).show()
            }
            activity?.fab?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_search))
            subscribeUi(this)
        }.root
    }

    private fun subscribeUi(binding: FragmentDashboardBinding) {
        binding.apply {
            dashboardViewModel.currentDiver.observe(
                viewLifecycleOwner,
                Observer {
                    diver = it
                    welcomeCertLevel.text = diver?.certifications?.first()?.certificationName
                }
            )
            getGearProfiles { gearProfiles ->
                Log.d("GEAR PROFILE RESULT", "$gearProfiles")
                gearProfiles?.forEach { profile ->
                    profile.gearList?.forEach {
                        getGearFromGearProfiles(it) { gear ->
                            Log.d(TAG_POJO, "GEAR ITEM: $gear")
                        }
                    }
                }
            }
            getEcoDiveCenter {
                Log.d(TAG_POJO, "DIVE CENTER: $it")
            }
            getDiveSiteCatalina {
                Log.d(TAG_POJO, "DIVE SITE: $it")
            }
            configureToolbar()
            configureUpcomingDivesRecyclerView()
            configureDashboardNotificationsRecyclerView()
            configureDashItems()
        }
    }

    private fun FragmentDashboardBinding.configureToolbar() {
        dashboardToolbar.apply {
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_profile -> {
                        navigateToProfile(this, diver!!)
                        true
                    }
                    R.id.action_saved -> {
                        navigateToSaved(this)
                        true
                    }
                    R.id.action_inbox -> {
                        navigateToInbox(this)
                        true
                    }
                    else -> true
                }
            }
        }
    }

    private fun FragmentDashboardBinding.configureUpcomingDivesRecyclerView() {
        val diveSites: List<DiveSite> = listOf(
            DiveSite("Shaw's Cove", rating = 4.5, reviews = 112),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Se lion Point", rating = 4.8, reviews = 86),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
        )
        upcomingDivesRv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveSiteAdapter(diveSites)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun FragmentDashboardBinding.configureDashItems() {
        welcomeDash.apply {
            dashWeatherCard.setOnClickListener { navigateToWeatherDetail(it) }
            dashFlyCard.setOnClickListener { navigateToDiveLogEntry(it) }
            dashMaintenanceCard.setOnClickListener { navigateToProfile(it, diver!!) }
            dashNextCard.setOnClickListener { navigateToCertificationDetail(it) }
        }
    }

    private fun FragmentDashboardBinding.configureDashboardNotificationsRecyclerView() {
        val inboxNotifications: ArrayList<InboxNotification> = arrayListOf(
            InboxNotification("Today", "This is a Notification"),
            InboxNotification("Feb 3rd", "This is a Notification"),
            InboxNotification("Jan 30th", "This is a Notification"),
            InboxNotification("Jan 18th", "This is a Notification"),
            InboxNotification("Dec 20th", "This is a Notification"),
            InboxNotification("Dec 3rd", "This is a Notification")
        )
        dashboardNotificationsRv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = NotificationAdapter(inboxNotifications, true)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun navigateToDiveLogEntry(view: View) {
        view.findNavController().navigate(R.id.navigation_logbook)
    }

    private fun navigateToWeatherDetail(view: View) {
        view.findNavController().navigate(R.id.weatherDetailFragment)
    }

    private fun navigateToSaved(view: View) {
        view.findNavController().navigate(R.id.savedFragment)
    }

    private fun navigateToProfile(view: View, diver: Diver) {
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
        view.findNavController().navigate(directions)
    }

    private fun navigateToInbox(view: View) {
        view.findNavController().navigate(R.id.inboxFragment)
    }

    private fun navigateToCertificationDetail(view: View) {
        val nextCertification = CatalogCertification("Rescue Diver")
        val directions = MainNavigationDirections.actionGlobalCertDetailFragment(nextCertification.name!!)
        view.findNavController().navigate(directions)
    }

    private fun getGearProfiles(gearProfiles: (List<GearProfile>?) -> Unit) {
        firestore.collection("divers/lEnWGcqDvI87XZvieJfY/gearProfiles")
            .get()
            .addOnSuccessListener { result ->
                gearProfiles(result?.toObjects<GearProfile>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error adding document", exception)
            }
    }

    private fun getGearFromGearProfiles(docRef: DocumentReference, gear: (Gear?) -> Unit) {
        firestore.document(docRef.path)
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "GET GEAR FROM PROFILE RESULT: $result")

                gear(result?.toObject<Gear>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error adding document", exception)
            }
    }

    private fun getAllDiveCenters() {
        firestore.collection("diveCenters")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG_FIRESTORE, "Read Dive Centers: ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    private fun getEcoDiveCenter(diveCenter: (DiveCenter?) -> Unit) {
        firestore.collection("diveCenters").document("3eyk7ZbWvWWFtQ71N7VA")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "Read Dive Center: ${result.id} => ${result.data}")
                diveCenter(result?.toObject<DiveCenter>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    private fun getAllDiveSites() {
        firestore.collection("diveSites")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG_FIRESTORE, "Read Dive Sites: ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    private fun getDiveSiteCatalina(diveSite: (DiveSite?) -> Unit) {
        firestore.collection("diveSites").document("dyhJj73cKyKtptsj6wLJ")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "Read Dive Site: ${result.id} => ${result.data}")
                diveSite(result?.toObject<DiveSite>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }
}
