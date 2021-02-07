package es.vass.brastlewark.ui.brastlewark

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.vass.brastlewark.data.domain.model.brastlewark.BrastlewarkModel
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.data.domain.model.error.ErrorModel
import es.vass.brastlewark.data.repository.DataProvider
import es.vass.brastlewark.data.repository.DataSourceCallbacks
import es.vass.brastlewark.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class BrastlewarkViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val dataProvider: DataProvider) :
    BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = BrastlewarkViewModel::class.qualifiedName

    val brastlewarktMutableLiveData: MutableLiveData<BrastlewarkModel> = MutableLiveData()
    val gnomeSelectedMutableLiveData: MutableLiveData<GnomeModel> = MutableLiveData()

    fun getGnomeListBrastlewark() {
        loadingMutableLiveData.postValue(true)
        dataProvider.getBrastlewark(object : DataSourceCallbacks.GetBrastlewarkCallback {
            override fun onGetBrastlewarkCallbackSuccess(brastlewarkModel: BrastlewarkModel) {
                brastlewarktMutableLiveData.postValue(brastlewarkModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBrastlewarkCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBrastlewarkCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        })
    }

    fun setGnomeSelected(gnomeModel: GnomeModel) {
        gnomeSelectedMutableLiveData.postValue(gnomeModel)
    }
}