package es.vass.brastlewark.data.repository.remote.mapper.brastlewark

import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.data.repository.remote.mapper.RequestMapper
import es.vass.brastlewark.data.repository.remote.mapper.ResponseMapper
import es.vass.brastlewark.data.repository.remote.responses.brastlewark.GnomeResponse
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class GnomeMapper : RequestMapper<GnomeModel, GnomeResponse>, ResponseMapper<GnomeResponse, GnomeModel> {
    override fun toRequest(model: GnomeModel): GnomeResponse {
        return GnomeResponse(
            model.id,
            model.name,
            model.thumbnail,
            model.age,
            model.weight,
            model.height,
            model.hairColor,
            model.professions,
            model.friends,
        )
    }

    override fun fromResponse(response: GnomeResponse): GnomeModel {
        return GnomeModel(
            response.id,
            response.name,
            response.thumbnail,
            response.age,
            "%.2f".format(Locale.US, response.weight).toDouble(),
            "%.2f".format(Locale.US, response.height).toDouble(),
            response.hair_color,
            response.professions,
            response.friends,
            GnomeModel.getSexGnome(response.name),
            GnomeModel.hairColorCode(response.hair_color),
        )
    }
}