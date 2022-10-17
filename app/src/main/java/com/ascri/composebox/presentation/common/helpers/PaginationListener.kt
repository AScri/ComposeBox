package com.ascri.composebox.presentation.common.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationListener(
    private val linearLayoutManager: LinearLayoutManager,
    val pageSize: Int = 10,
    var currentPage: Int = 1,
    private val threshold: Int = 0,
    private val onLoadMoreItem: (page: Int) -> Unit
) : RecyclerView.OnScrollListener() {
    var isLastPage = false
    var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= pageSize) {
                currentPage++
                onLoadMoreItem(currentPage)
                isLoading = true
            }
        }
    }

    fun restart() {
        currentPage = 1
        isLastPage = false
        isLoading = false
    }
}