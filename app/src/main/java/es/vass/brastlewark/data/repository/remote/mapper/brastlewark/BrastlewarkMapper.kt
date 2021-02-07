package es.vass.brastlewark.data.repository.remote.mapper.brastlewark

import es.vass.brastlewark.data.domain.model.brastlewark.BrastlewarkModel
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.data.repository.remote.mapper.RequestMapper
import es.vass.brastlewark.data.repository.remote.mapper.ResponseMapper
import es.vass.brastlewark.data.repository.remote.responses.brastlewark.BrastlewarkResponse
import es.vass.brastlewark.data.repository.remote.responses.brastlewark.GnomeResponse

class BrastlewarkMapper : RequestMapper<BrastlewarkModel, BrastlewarkResponse>, ResponseMapper<BrastlewarkResponse, BrastlewarkModel> {
    override fun toRequest(model: BrastlewarkModel): BrastlewarkResponse {
        val brastlewarkResponse = BrastlewarkResponse()
        val gnomeMapper = GnomeMapper()
        val gnomeList: ArrayList<GnomeResponse> = ArrayList()

        model.gnomesList.forEach { gnomeModel ->
            gnomeList.add(gnomeMapper.toRequest(gnomeModel))
        }

        brastlewarkResponse.gnomesList = gnomeList

        return brastlewarkResponse
    }

    override fun fromResponse(response: BrastlewarkResponse): BrastlewarkModel {
        val brastlewarkModel = BrastlewarkModel()
        val gnomeMapper = GnomeMapper()
        val gnomeList: ArrayList<GnomeModel> = ArrayList()

        response.gnomesList.forEach { gnomeResponse ->
            gnomeList.add(gnomeMapper.fromResponse(gnomeResponse))
        }

        brastlewarkModel.gnomesList = gnomeList

        return brastlewarkModel
    }
}