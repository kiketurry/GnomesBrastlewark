package es.vass.brastlewark.data.domain.model.brastlewark

import android.util.Log
import es.vass.brastlewark.data.domain.model.BaseModel
import java.util.*
import kotlin.collections.ArrayList

data class GnomeModel(
    var id: Int = 0,
    var name: String = "",
    var thumbnail: String = "",
    var age: Int = 0,
    var weight: Double = 0.0,
    var height: Double = 0.0,
    var hairColor: String = "",
    var professions: ArrayList<String> = ArrayList(),
    var friends: ArrayList<String> = ArrayList(),
    var sex: SexGnome = SexGnome.GNOME_SEX_HERMAPHRODITE,
    var hairColorCode: HairColorCodeGnome = HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_UNKNOW,
) : BaseModel() {
    enum class SexGnome { GNOME_SEX_MALE, GNOME_SEX_FEMALE, GNOME_SEX_HERMAPHRODITE }
    enum class HairColorCodeGnome { GNOME_HAIR_COLOR_CODE_UNKNOW, GNOME_HAIR_COLOR_CODE_PINK, GNOME_HAIR_COLOR_CODE_GREEN, GNOME_HAIR_COLOR_CODE_RED, GNOME_HAIR_COLOR_CODE_BLACK, GNOME_HAIR_COLOR_CODE_GRAY }

    companion object {

        private val TAG: String? get() = GnomeModel::class.qualifiedName

        private const val MALE_VALUE_A = 2
        private const val MALE_VALUE_I = 1
        private const val MALE_VALUE_O = 2
        private const val MALE_VALUE_U = 1

        fun getSexGnome(name: String): SexGnome {
            var male = 0
            var female = 0

            name.let { it ->
                var countA = 0
                var countI = 0
                var countO = 0
                var countU = 0

                countA = it.count { "a".contains(it, ignoreCase = true) }
                countI = it.count { "i".contains(it, ignoreCase = true) }
                countO = it.count { "o".contains(it, ignoreCase = true) }
                countU = it.count { "u".contains(it, ignoreCase = true) }

                male = countO * MALE_VALUE_O + countU * MALE_VALUE_U
                female = countA * MALE_VALUE_A + countI * MALE_VALUE_I
            }

            return when {
                male > female -> {
                    SexGnome.GNOME_SEX_MALE
                }
                female > male -> {
                    SexGnome.GNOME_SEX_FEMALE
                }
                else -> {
                    SexGnome.GNOME_SEX_HERMAPHRODITE
                }
            }
        }

        fun hairColorCode(hairColor: String): HairColorCodeGnome {
            when (hairColor.toLowerCase(Locale.getDefault())) {
                "pink" -> {
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_PINK
                }
                "green" -> {
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_GREEN
                }
                "red" -> {
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_RED
                }
                "black" -> {
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_BLACK
                }
                "gray" -> {
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_GRAY
                }
                else -> {
                    Log.d(TAG, "l> No conocemos el color $hairColor")
                    return HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_UNKNOW
                }
            }
        }
    }
}