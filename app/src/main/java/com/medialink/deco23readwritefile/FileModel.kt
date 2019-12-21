package com.medialink.deco23readwritefile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FileModel (
    var filename: String? = null,
    var data: String? = null
) : Parcelable