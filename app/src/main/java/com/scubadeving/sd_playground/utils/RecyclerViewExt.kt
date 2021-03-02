package com.scubadeving.sd_playground.utils

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.configureHorizontalRecyclerView(
    targetAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    toggleSnapHelper: Boolean = true
) {
    layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    adapter = targetAdapter
    val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
    addItemDecoration(dividerItemDecoration)
    if (toggleSnapHelper) {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }
}
