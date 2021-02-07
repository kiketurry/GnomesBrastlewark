package es.vass.brastlewark.data.repository.remote

import android.util.Log
import es.vass.brastlewark.data.domain.model.error.ErrorModel
import es.vass.brastlewark.data.repository.DataSourceCallbacks
import es.vass.brastlewark.data.repository.remote.mapper.brastlewark.BrastlewarkMapper
import es.vass.brastlewark.data.repository.remote.responses.brastlewark.BrastlewarkResponse
import es.vass.brastlewark.utils.ErrorsUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallbacks {
    companion object {
        private val TAG: String? = ErrorsUtils::class.simpleName

        fun getBreedsCallback(getBreedsCallback: DataSourceCallbacks.GetBrastlewarkCallback): Callback<BrastlewarkResponse> {
            return object : Callback<BrastlewarkResponse> {
                override fun onResponse(call: Call<BrastlewarkResponse>, response: Response<BrastlewarkResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getBreedsCallback.")
                        getBreedsCallback.onGetBrastlewarkCallbackSuccess(BrastlewarkMapper().fromResponse(response.body()!!))
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getBreedsCallback.")
                        getBreedsCallback.onGetBrastlewarkCallbackUnsuccess(ErrorsUtils.generateErrorModelFromResponseErrorBody(response.errorBody()))
                    }
                }

                override fun onFailure(call: Call<BrastlewarkResponse>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getBreedsCallback failure.")
                    getBreedsCallback.onGetBrastlewarkCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }
    }
}