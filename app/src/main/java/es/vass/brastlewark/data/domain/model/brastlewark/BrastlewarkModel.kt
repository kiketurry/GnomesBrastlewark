package es.vass.brastlewark.data.domain.model.brastlewark

import es.vass.brastlewark.data.domain.model.BaseModel

data class BrastlewarkModel(
    var gnomesList: ArrayList<GnomeModel> = ArrayList(),
) : BaseModel()