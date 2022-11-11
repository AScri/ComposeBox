package com.ascri.composebox.utils.extensions

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}

inline fun <reified T : Any> Any.castOrNull(): T? {
    return this as? T
}