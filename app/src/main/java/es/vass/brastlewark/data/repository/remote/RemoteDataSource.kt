package es.vass.brastlewark.data.repository.remote

import es.vass.brastlewark.data.repository.DataSource
import es.vass.brastlewark.data.repository.DataSourceCallbacks

class RemoteDataSource(private val apiServiceBrastlewark: ApiServicesBrastlewark) : DataSource {
    companion object {
        var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(apiServiceBrastlewark: ApiServicesBrastlewark): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiServiceBrastlewark)
            }
            return INSTANCE!!
        }
    }

    override fun getBrastlewark(getBrastlewarkCallback: DataSourceCallbacks.GetBrastlewarkCallback) {
        val getBrastlewarkCall = apiServiceBrastlewark.getBrastlewark()
        getBrastlewarkCall.enqueue(RetrofitCallbacks.getBreedsCallback(getBrastlewarkCallback))
    }
}