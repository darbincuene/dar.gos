package com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos

import com.luisavillacorte.gosportapp.jugador.adapters.model.auth.PerfilUsuarioResponse

interface HomeCampeonatosContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showCampeonatos(campeonato: List<Campeonatos>)
        fun showError(message: String)
        fun traernombre(perfilUsuarioResponse: PerfilUsuarioResponse)
        fun showImages(images: List<ImageData>)
    }

    interface Presenter {
        fun getCampeonatos()
        fun getPerfilUsuario()
        fun getImages()


    }
}