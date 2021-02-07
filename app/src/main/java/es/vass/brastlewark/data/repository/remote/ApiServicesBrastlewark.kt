package es.vass.brastlewark.data.repository.remote

import es.vass.brastlewark.data.repository.remote.responses.brastlewark.BrastlewarkResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServicesBrastlewark {

    //API LIST
    //############
    @GET("master/data.json")
    fun getBrastlewark(
    ): Call<BrastlewarkResponse>

}