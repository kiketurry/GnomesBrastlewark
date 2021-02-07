package es.vass.brastlewark.data.repository

class DataProvider private constructor(private val remoteDataSource: DataSource) : DataSource {
    companion object {
        var INSTANCE: DataProvider? = null

        @Synchronized
        fun getInstance(remoteDataSource: DataSource): DataProvider {
            if (INSTANCE == null) {
                INSTANCE = DataProvider(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getBrastlewark(getBreedsCallback: DataSourceCallbacks.GetBrastlewarkCallback) {
        remoteDataSource.getBrastlewark(getBreedsCallback)
    }
}