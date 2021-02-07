package es.vass.brastlewark.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.vass.brastlewark.data.repository.DataProvider
import es.vass.brastlewark.ui.brastlewark.BrastlewarkViewModel
import es.vass.brastlewark.ui.brastlewark.detailgnome.DetailGnomeViewModel
import es.vass.brastlewark.ui.brastlewark.gnomelist.GnomeListViewModel

class ViewModelFactory(
    val application: Application,
    val dataProvider: DataProvider,
) :
    ViewModelProvider.Factory {
    companion object {
        var INSTANCE: ViewModelFactory? = null

        @Synchronized
        fun getInstance(
            application: Application,
            dataProvider: DataProvider
        ): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, dataProvider)
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = when (modelClass.name) {
            BrastlewarkViewModel::class.qualifiedName -> {
                BrastlewarkViewModel(application = application, dataProvider = dataProvider)
            }
            GnomeListViewModel::class.qualifiedName -> {
                GnomeListViewModel(application = application)
            }
            DetailGnomeViewModel::class.qualifiedName -> {
                DetailGnomeViewModel(application = application)
            }
            else -> {
                SimplyViewModel(application = application)
            }
        }

        return viewModel as T
    }
}