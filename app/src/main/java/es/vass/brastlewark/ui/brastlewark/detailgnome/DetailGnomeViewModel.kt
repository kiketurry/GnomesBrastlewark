package es.vass.brastlewark.ui.brastlewark.detailgnome

import android.app.Application
import es.vass.brastlewark.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DetailGnomeViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = DetailGnomeViewModel::class.qualifiedName

}