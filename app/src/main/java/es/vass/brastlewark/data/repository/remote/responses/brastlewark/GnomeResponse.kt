package es.vass.brastlewark.data.repository.remote.responses.brastlewark

import com.google.gson.annotations.SerializedName

class GnomeResponse(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("thumbnail") var thumbnail: String = "",
    @SerializedName("age") val age: Int = 0,
    @SerializedName("weight") val weight: Double = 0.0,
    @SerializedName("height") val height: Double = 0.0,
    @SerializedName("hair_color") val hair_color: String = "",
    @SerializedName("professions") val professions: ArrayList<String> = ArrayList(),
    @SerializedName("friends") val friends: ArrayList<String> = ArrayList(),
)