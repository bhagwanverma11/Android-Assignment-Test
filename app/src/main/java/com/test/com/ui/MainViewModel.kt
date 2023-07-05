package com.test.com.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.com.data.Movie
import com.test.com.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(){

    private var _movieResponse = MutableLiveData<NetworkResult<List<Movie>>>()
    val movieResponse: LiveData<NetworkResult<List<Movie>>> = _movieResponse

    init {
        callMovieList()
    }

    private fun callMovieList() {
        viewModelScope.launch {
            mainRepository.getPopularMovies().collect {
                _movieResponse.postValue(it)
            }
        }
    }

}