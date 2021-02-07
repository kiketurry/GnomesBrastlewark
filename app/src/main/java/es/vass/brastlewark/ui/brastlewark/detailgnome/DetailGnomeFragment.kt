package es.vass.brastlewark.ui.brastlewark.detailgnome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import es.vass.brastlewark.R
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.databinding.FragmentBrastlewarkGnomeDetailBinding
import es.vass.brastlewark.extensions.glideWithHeaders
import es.vass.brastlewark.extensions.gone
import es.vass.brastlewark.extensions.invisible
import es.vass.brastlewark.extensions.visible
import es.vass.brastlewark.injection.InjectionSingleton
import es.vass.brastlewark.ui.base.BaseFragment
import es.vass.brastlewark.ui.brastlewark.BrastlewarkActivity

class DetailGnomeFragment : BaseFragment<FragmentBrastlewarkGnomeDetailBinding>() {
    override val TAG: String? get() = DetailGnomeFragment::class.qualifiedName

    lateinit var detailGnomeViewModel: DetailGnomeViewModel

    override fun inflateBinding() {
        binding = FragmentBrastlewarkGnomeDetailBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        detailGnomeViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(DetailGnomeViewModel::class.java)
    }

    override fun observeViewModel() {
        detailGnomeViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        detailGnomeViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BrastlewarkActivity).brastlewarkViewModel.gnomeSelectedMutableLiveData.observe(viewLifecycleOwner, this::populateDataGnome)
    }

    private fun populateDataGnome(gnomeModel: GnomeModel) {
        binding?.ivGnome?.glideWithHeaders(requireContext(), gnomeModel.thumbnail)
        binding?.tvName?.text = gnomeModel.name
        binding?.tvAge?.text = getString(R.string.gnomeAge, gnomeModel.age.toString())
        binding?.tvHeight?.text = getString(R.string.gnomeHeight, gnomeModel.height.toString())
        binding?.tvWeight?.text = getString(R.string.gnomeWeight, gnomeModel.weight.toString())

        when (gnomeModel.hairColorCode) {
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_UNKNOW -> {
                binding?.vwColorHair?.invisible()
            }
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_PINK -> binding?.vwColorHair?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gnomeHairColorCodePink
                )
            )
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_GREEN -> binding?.vwColorHair?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gnomeHairColorCodeGreen
                )
            )
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_RED -> binding?.vwColorHair?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gnomeHairColorCodeRed
                )
            )
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_BLACK -> binding?.vwColorHair?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gnomeHairColorCodeBlack
                )
            )
            GnomeModel.HairColorCodeGnome.GNOME_HAIR_COLOR_CODE_GRAY -> binding?.vwColorHair?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gnomeHairColorCodeGray
                )
            )
        }

        when (gnomeModel.sex) {
            GnomeModel.SexGnome.GNOME_SEX_MALE -> {
                binding?.tvSex?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_male, 0, 0, 0)
                binding?.tvSex?.text = getString(R.string.gnomeSexMale)
            }
            GnomeModel.SexGnome.GNOME_SEX_FEMALE -> {
                binding?.tvSex?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_female, 0, 0, 0)
                binding?.tvSex?.text = getString(R.string.gnomeSexFemale)
            }
            GnomeModel.SexGnome.GNOME_SEX_HERMAPHRODITE -> {
                binding?.tvSex?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hermaphrodite, 0, 0, 0)
                binding?.tvSex?.text = getString(R.string.gnomeSexHermaphrodite)
            }
        }

        if (gnomeModel.professions.isNotEmpty()) {
            binding?.tvProfessions?.visible()
            binding?.tvProfessionsData?.visible()
            binding?.tvProfessionsData?.text = gnomeModel.professions.toString()
        } else {
            binding?.tvProfessions?.gone()
            binding?.tvProfessionsData?.gone()
        }

        if (gnomeModel.professions.isNotEmpty()) {
            binding?.tvFriends?.visible()
            binding?.tvFriendsData?.visible()
            binding?.tvFriendsData?.text = gnomeModel.friends.toString()
        } else {
            binding?.tvFriends?.gone()
            binding?.tvFriendsData?.gone()
        }
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarDetailGnome))
        baseActivity.showBackToolbar(true)
        baseActivity.showCloseToolbar(false)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit
}