package app.com.access.model
import com.google.gson.annotations.SerializedName

data class Detail(

    @SerializedName("name")
    val name: String = "",
    @SerializedName("bio")
    val bio: String = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("location")
    val location: String = "",
    @SerializedName("blog")
    val blog: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("login")
    val login: String = ""
)