package es.vass.brastlewark.data.repository.remote.mapper

interface RequestMapper<M, E> {
    fun toRequest(model: M): E
}