package es.vass.brastlewark.data.repository.remote

import es.vass.brastlewark.BuildConfig
import es.vass.brastlewark.data.constants.GeneralConstants.Companion.RETROFIT_TIMEOUT_IN_SECOND
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RetrofitClientBrastlewark() {
    companion object {
        var INSTANCE: RetrofitClientBrastlewark? = null

        @Synchronized
        fun getInstance(): RetrofitClientBrastlewark {
            if (INSTANCE == null) {
                INSTANCE = RetrofitClientBrastlewark()
            }
            return INSTANCE!!
        }
    }

    private val retrofit: Retrofit
    private val apiServicesBrastlewark: ApiServicesBrastlewark

    init {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)

        httpClient.interceptors().clear()

        if (BuildConfig.DEBUG) {
            // Creamos un interceptor y le indicamos el log level a usar
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
        apiServicesBrastlewark = retrofit.create(ApiServicesBrastlewark::class.java)
    }

    fun getApiServices(): ApiServicesBrastlewark {
        return apiServicesBrastlewark
    }
}