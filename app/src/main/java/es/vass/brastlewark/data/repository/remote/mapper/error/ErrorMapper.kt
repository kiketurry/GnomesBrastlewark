package es.vass.brastlewark.data.repository.remote.mapper.error

import es.vass.brastlewark.data.domain.model.error.ErrorModel
import es.vass.brastlewark.data.repository.remote.mapper.RequestMapper
import es.vass.brastlewark.data.repository.remote.mapper.ResponseMapper
import es.vass.brastlewark.data.repository.remote.responses.error.ErrorResponse

class ErrorMapper : RequestMapper<ErrorModel, ErrorResponse>, ResponseMapper<ErrorResponse, ErrorModel> {
    override fun toRequest(model: ErrorModel): ErrorResponse {
        return ErrorResponse(model.message)
    }

    override fun fromResponse(response: ErrorResponse): ErrorModel {
        return ErrorModel(response.message)
    }
}