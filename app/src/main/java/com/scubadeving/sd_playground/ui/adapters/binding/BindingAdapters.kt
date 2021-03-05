package com.scubadeving.sd_playground.ui.adapters.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.divelog.Buddy

@BindingAdapter("diveCountText")
fun bindDiveCountText(textView: TextView, diveCount: Int) {
    val resources = textView.context.resources
    val diveCountString =
        resources.getQuantityString(R.plurals.dive_count_suffix, diveCount, diveCount)

    textView.text = diveCountString
}

@BindingAdapter("buddyCountText")
fun bindBuddyCountText(textView: TextView, buddies: List<Buddy>?) {
    val resources = textView.context.resources
    val buddyString = buddies?.size?.let {
        resources.getQuantityString(R.plurals.buddy_count_suffix, it, it)
    }

    textView.text = buddyString
}
