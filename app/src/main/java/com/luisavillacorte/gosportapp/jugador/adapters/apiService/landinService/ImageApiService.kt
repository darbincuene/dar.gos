package com.luisavillacorte.gosportapp.jugador.adapters.apiService.landinService

import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageData
import retrofit2.Call
import retrofit2.http.GET

interface ImageApiService {
    @GET("photo")
    fun getImages(): Call<List<ImageData>>
}