package es.vass.brastlewark.data.repository

import es.vass.brastlewark.data.domain.model.brastlewark.BrastlewarkModel
import es.vass.brastlewark.data.domain.model.error.ErrorModel

interface DataSourceCallbacks {
    interface GetBrastlewarkCallback {
        fun onGetBrastlewarkCallbackSuccess(brastlewarkModel: BrastlewarkModel)

        fun onGetBrastlewarkCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetBrastlewarkCallbackFailure(errorModel: ErrorModel)
    }
}