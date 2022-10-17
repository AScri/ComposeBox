package com.ascri.cleanapp.presentation.common.helpers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ascri.cleanapp.utils.extensions.px

class LastItemSpaceDecorator(
    private val lastItemSpaceDp: Float,
    private val isVertical: Boolean = true
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
            if (isVertical) outRect.bottom = lastItemSpaceDp.px
            else outRect.right = lastItemSpaceDp.px
        }
    }
}