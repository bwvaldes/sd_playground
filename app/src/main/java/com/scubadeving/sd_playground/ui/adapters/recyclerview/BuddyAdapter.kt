package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.model.diver.Diver.Companion.VIEW_TYPE_HORIZONTAL
import com.scubadeving.sd_playground.data.model.diver.Diver.Companion.VIEW_TYPE_VERTICAL
import com.scubadeving.sd_playground.databinding.ItemExploreBuddyCardHorizontalBinding
import com.scubadeving.sd_playground.databinding.ItemExploreBuddyCardVerticalBinding

class BuddyAdapter(val buddies: ArrayList<Diver>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_HORIZONTAL -> {
                BuddyHorizontalViewHolder(
                    ItemExploreBuddyCardHorizontalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_VERTICAL -> {
                BuddyVerticalViewHolder(
                    ItemExploreBuddyCardVerticalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return if (buddies.size > BUDDY_VERTICAL_LIMIT) {
            BUDDY_VERTICAL_LIMIT
        } else {
            buddies.size
        }
    }

    override fun getItemViewType(position: Int): Int = buddies[position].diverType

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val buddy = buddies[position]
        when (holder) {
            is BuddyHorizontalViewHolder -> holder.bind(buddy)
            is BuddyVerticalViewHolder -> holder.bind(buddy)
            else -> throw IllegalArgumentException()
        }
    }

    inner class BuddyHorizontalViewHolder(private val binding: ItemExploreBuddyCardHorizontalBinding) :
        BaseViewHolder<Diver>(binding.root) {

        override fun bind(diver: Diver) {
            binding.apply {
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Buddy Card!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToProfile(it, diver)
                }
                diverCardHorizontalClear.setOnClickListener { dismissBuddy(position) }
                diverCardHorizontalAvatar.setImageResource(R.drawable.ic_menu_avatar)
                diverCardHorizontalBackground.setImageResource(R.color.purple_200)
                diverCardHorizontalName.text = diver.firstName
                diverCardHorizontalLevel.text = diver.certifications?.first()?.certificationName
                diverCardHorizontalAddBuddy.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Add Buddy!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    inner class BuddyVerticalViewHolder(private val binding: ItemExploreBuddyCardVerticalBinding) :
        BaseViewHolder<Diver>(binding.root) {

        override fun bind(diver: Diver) {
            binding.apply {
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Buddy Card!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToProfile(it, diver)
                }
                diverCardVerticalClear.setOnClickListener { dismissBuddy(position) }
                diverCardVerticalAvatar.setImageResource(R.drawable.ic_menu_avatar)
                diverCardVerticalBackground.setImageResource(R.color.teel_200)
                diverCardVerticalName.text = diver.firstName
                diverCardVerticalLevel.text = diver.certifications?.first()?.certificationName
                diverCardVerticalAddBuddy.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Add Buddy!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun dismissBuddy(position: Int) {
        buddies.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, buddies.size)
    }

    private fun navigateToProfile(view: View, diver: Diver) {
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
        view.findNavController().navigate(directions)
    }

    companion object {
        private const val BUDDY_VERTICAL_LIMIT = 4
    }
}
