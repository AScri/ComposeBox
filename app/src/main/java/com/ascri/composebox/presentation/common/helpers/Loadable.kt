package com.ascri.composebox.presentation.common.helpers

sealed class Loadable<K> {
    class Item<T>(val item: T) : Loadable<T>()
    class Loader<T>(val id: Long) : Loadable<T>()

    companion object {
        const val ITEM_TYPE = 0
        const val LOADER_TYPE = 1
    }
}