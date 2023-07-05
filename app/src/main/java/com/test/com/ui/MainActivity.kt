package com.test.com.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import android.widget.Toast
import androidx.core.view.isVisible
import com.test.com.data.NetworkResult
import com.test.com.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val mainViewModel:MainViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.rvMovies.adapter = movieAdapter

        mainViewModel.movieResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    mainBinding.progressbar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Log.e("TAG", "onCreate: "+it.errorMessage)
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    mainBinding.progressbar.isVisible = false
                }

                is  NetworkResult.Success -> {
                    movieAdapter.updateMovies(it.data)
                    mainBinding.progressbar.isVisible = false
                }
            }
        }

    }
}