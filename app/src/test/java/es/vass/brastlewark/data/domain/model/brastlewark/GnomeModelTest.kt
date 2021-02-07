package es.vass.brastlewark.data.domain.model.brastlewark

import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel.HairColorCodeGnome.*
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel.SexGnome.*
import org.junit.Assert
import org.junit.Test

class GnomeModelTest {
    @Test
    fun checkSexGnomeModelCalculatorTest() {
        Assert.assertEquals(GNOME_SEX_MALE, GnomeModel.getSexGnome("Koldo"))
        Assert.assertEquals(GNOME_SEX_FEMALE, GnomeModel.getSexGnome("Sara"))
        Assert.assertEquals(GNOME_SEX_HERMAPHRODITE, GnomeModel.getSexGnome("Cotera"))
    }

    @Test
    fun checkColorHairCalculatorTest() {
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_PINK, GnomeModel.hairColorCode("PinK"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_GREEN, GnomeModel.hairColorCode("green"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_RED, GnomeModel.hairColorCode("rEd"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_BLACK, GnomeModel.hairColorCode("BLACK"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_GRAY, GnomeModel.hairColorCode("GrAY"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_UNKNOW, GnomeModel.hairColorCode("asdf"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_UNKNOW, GnomeModel.hairColorCode("Yellow"))
        Assert.assertEquals(GNOME_HAIR_COLOR_CODE_UNKNOW, GnomeModel.hairColorCode(""))
    }
}