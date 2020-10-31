package app.com.access.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("login")
    val login: String = "",
    @SerializedName("id")
    val id: Int = 0

)