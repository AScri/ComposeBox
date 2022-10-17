package com.ascri.cleanapp.domain.model.app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val username: String,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    var photo: String = "",
) : Parcelable