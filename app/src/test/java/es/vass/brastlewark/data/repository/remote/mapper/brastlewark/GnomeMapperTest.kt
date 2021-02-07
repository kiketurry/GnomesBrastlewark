package es.vass.brastlewark.data.repository.remote.mapper.brastlewark

import es.vass.brastlewark.data.repository.remote.responses.brastlewark.GnomeResponse
import org.junit.Assert
import org.junit.Test

class GnomeMapperTest {

    @Test
    fun gnomeResponseToGnomeModelTest() {
        val gnomeResponse = GnomeResponse(
            1,
            "nombre",
            "http://prueba.jpg",
            123,
            230.45789,
            123.9278,
            "RED",
            arrayListOf("cura", "carcelero", "bombero"),
            arrayListOf("pepe", "juan", "óscar")
        )
        val gnomeModel = GnomeMapper().fromResponse(gnomeResponse)
        Assert.assertEquals("nombre", gnomeModel.name)
        Assert.assertEquals(230.46, gnomeModel.weight, 0.0)
        Assert.assertEquals(123.93, gnomeModel.height, 0.0)
    }
}