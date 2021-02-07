package es.vass.brastlewark.ui.brastlewark.gnomelist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.vass.brastlewark.R
import es.vass.brastlewark.data.domain.model.brastlewark.BrastlewarkModel
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.databinding.FragmentBrastlewarkListGnomesBinding
import es.vass.brastlewark.extensions.gone
import es.vass.brastlewark.extensions.invisible
import es.vass.brastlewark.extensions.visible
import es.vass.brastlewark.injection.InjectionSingleton
import es.vass.brastlewark.ui.base.BaseFragment
import es.vass.brastlewark.ui.brastlewark.BrastlewarkActivity
import es.vass.brastlewark.ui.brastlewark.gnomelist.adapter.GnomeListAdapter
import es.vass.brastlewark.ui.brastlewark.gnomelist.adapter.GnomeListAdapter.ItemGnomeClickListener

class GnomeListFragment : BaseFragment<FragmentBrastlewarkListGnomesBinding>(), ItemGnomeClickListener {
    override val TAG: String? get() = GnomeListFragment::class.qualifiedName

    lateinit var gnomeListViewModel: GnomeListViewModel

    private lateinit var gnomeListAdapter: GnomeListAdapter

    override fun inflateBinding() {
        binding = FragmentBrastlewarkListGnomesBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        gnomeListViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(GnomeListViewModel::class.java)
    }

    override fun observeViewModel() {
        gnomeListViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        gnomeListViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BrastlewarkActivity).brastlewarkViewModel.brastlewarktMutableLiveData.observe(viewLifecycleOwner, this::populateList)
        gnomeListViewModel.gnomeListShowMutableLiveData.observe(viewLifecycleOwner, { gnomeList ->
            showList(gnomeList)
            baseActivity.hideKeyboard()
        })
    }

    private fun showList(gnomeList: ArrayList<GnomeModel>) {
        if (gnomeList.isEmpty()) {
            binding?.tvEmptyFilterList?.visible()
        } else {
            binding?.tvEmptyFilterList?.gone()
        }
        gnomeListAdapter.refreshGnomes(gnomeList)
    }

    private fun populateList(brastlewarkModel: BrastlewarkModel) {
        gnomeListViewModel.setCompleteList(brastlewarkModel.gnomesList)
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarGnomeList))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(true)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        gnomeListAdapter = GnomeListAdapter(context!!, ArrayList(), this)

        binding?.rvGnomeList?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = gnomeListAdapter
        }

        binding?.etSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(searchText: CharSequence?, start: Int, before: Int, count: Int) {
                if (searchText?.length != null && searchText?.length > 0) {
                    binding?.ivClearSearch?.visible()
                } else {
                    binding?.ivClearSearch?.invisible()
                }
                gnomeListViewModel.cancelTimer()
            }

            override fun afterTextChanged(editable: Editable?) {
                gnomeListViewModel.filter(editable)
            }
        })

        binding?.ivClearSearch?.setOnClickListener {
            binding?.etSearch?.text?.clear()
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun onItemThingClick(gnomeModel: GnomeModel) {
        (baseActivity as BrastlewarkActivity).setGnomeSelected(gnomeModel)
        (baseActivity as BrastlewarkActivity).goToDetailGnomeFragment()
    }

}