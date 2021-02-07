package es.vass.brastlewark.injection

import android.app.Application
import android.content.Context
import es.vass.brastlewark.data.repository.DataProvider
import es.vass.brastlewark.data.repository.remote.ApiServicesBrastlewark
import es.vass.brastlewark.data.repository.remote.RemoteDataSource
import es.vass.brastlewark.data.repository.remote.RetrofitClientBrastlewark
import es.vass.brastlewark.ui.base.ViewModelFactory

class InjectionSingleton {
    companion object {
        private fun provideApiServicesBrastlewark(): ApiServicesBrastlewark {
            return RetrofitClientBrastlewark.getInstance().getApiServices()
        }

        private fun provideDataSource(): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideApiServicesBrastlewark()))
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            return ViewModelFactory.getInstance(
                context.applicationContext as Application,
                provideDataSource()
            )
        }
    }
}