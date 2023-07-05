package com.test.com.data

import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getMostPopularMovies() : List<Movie>

}