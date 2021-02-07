package es.vass.brastlewark.data.repository

interface DataSource {
    fun getBrastlewark(getBreedsCallback: DataSourceCallbacks.GetBrastlewarkCallback)
}