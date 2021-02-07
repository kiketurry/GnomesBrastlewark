package es.vass.brastlewark.ui.brastlewark

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.vass.brastlewark.R
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.databinding.ActivityBrastlewarkBinding
import es.vass.brastlewark.injection.InjectionSingleton
import es.vass.brastlewark.ui.base.BaseActivity
import es.vass.brastlewark.ui.brastlewark.detailgnome.DetailGnomeFragment
import es.vass.brastlewark.ui.brastlewark.gnomelist.GnomeListFragment

class BrastlewarkActivity : BaseActivity<ActivityBrastlewarkBinding>() {
    override val TAG: String? get() = BrastlewarkActivity::class.qualifiedName

    lateinit var brastlewarkViewModel: BrastlewarkViewModel

    override fun inflateBinding() {
        binding = ActivityBrastlewarkBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        brastlewarkViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(BrastlewarkViewModel::class.java)
    }

    override fun observeViewModel() {
        brastlewarkViewModel.loadingMutableLiveData.observe(this, this::showLoading)
        brastlewarkViewModel.errorMutableLiveData.observe(this, this::showError)
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        brastlewarkViewModel.getGnomeListBrastlewark()
        goToGnomeListFragment()
    }

    override fun configureToolbar() = Unit

    private fun goToGnomeListFragment() {
        val fragment: Fragment = GnomeListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, GnomeListFragment::class.qualifiedName)
            .addToBackStack(GnomeListFragment::class.qualifiedName)
            .commit()
    }

    fun goToDetailGnomeFragment() {
        val fragment: Fragment = DetailGnomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, DetailGnomeFragment::class.qualifiedName)
            .addToBackStack(DetailGnomeFragment::class.qualifiedName)
            .commit()
    }

    fun setGnomeSelected(gnomeModel: GnomeModel) {
        brastlewarkViewModel.setGnomeSelected(gnomeModel)
    }
}