package es.vass.brastlewark.data.repository.remote.mapper

interface ResponseMapper<E, M> {
    fun fromResponse(response: E) : M
}