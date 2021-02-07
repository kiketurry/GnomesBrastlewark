package es.vass.brastlewark.data.repository.remote.responses.brastlewark

import com.google.gson.annotations.SerializedName

class BrastlewarkResponse(
    @SerializedName("Brastlewark") var gnomesList: ArrayList<GnomeResponse> = ArrayList(),
)