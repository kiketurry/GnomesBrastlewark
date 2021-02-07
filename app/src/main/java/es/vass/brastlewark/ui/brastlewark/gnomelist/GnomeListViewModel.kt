package es.vass.brastlewark.ui.brastlewark.gnomelist

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.vass.brastlewark.data.constants.GeneralConstants.Companion.DELAY_FILTER_LIST_IN_MILLISECONDS
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*
import kotlin.collections.ArrayList

class GnomeListViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) :
    BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = GnomeListViewModel::class.qualifiedName

    val gnomeListShowMutableLiveData: MutableLiveData<ArrayList<GnomeModel>> = MutableLiveData()

    private val completeListGnomeModel: ArrayList<GnomeModel> = ArrayList()
    private val filterListGnomeModel: ArrayList<GnomeModel> = ArrayList()

    private var timerFilterGnomeList: Timer? = null

    fun setCompleteList(gnomesList: ArrayList<GnomeModel>) {
        completeListGnomeModel.clear()
        completeListGnomeModel.addAll(gnomesList)
        gnomeListShowMutableLiveData.postValue(completeListGnomeModel)
    }

    fun cancelTimer() {
        timerFilterGnomeList?.cancel()
    }

    fun filter(editable: Editable?) {
        if (!editable.isNullOrBlank()) {
            filterListGnomeModel.clear()
            val searchText = editable.toString()
            timerFilterGnomeList = Timer("FilterGnomeList", false)
            timerFilterGnomeList?.schedule(object : TimerTask() {
                override fun run() {
                    if (!completeListGnomeModel.isNullOrEmpty()) {
                        completeListGnomeModel.forEach { gnomeModel ->
                            if (gnomeModel.name.contains(searchText, true)) {
                                filterListGnomeModel.add(gnomeModel)
                            }
                        }
                        Log.d(TAG, "l> filtrado hecho, mandamos la lista")
                        gnomeListShowMutableLiveData.postValue(filterListGnomeModel)
                    }
                }
            }, DELAY_FILTER_LIST_IN_MILLISECONDS)
        } else {
            Log.d(TAG, "l> filtrado vacio, mandamos la lista entera")
            gnomeListShowMutableLiveData.postValue(completeListGnomeModel)
        }
    }
}