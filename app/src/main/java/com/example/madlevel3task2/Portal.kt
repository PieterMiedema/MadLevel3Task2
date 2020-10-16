package com.example.madlevel3task2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var portalTitle: String,
    var portalUrl: String
) : Parcelable