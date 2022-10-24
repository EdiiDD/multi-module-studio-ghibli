package com.edy.feature_listmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edy.core_domain.usecase.flow.Resource
import com.edy.core_domain_movie.model.Movie
import com.edy.core_domain_movie.usecase.GetListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Random
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getMoviesListUseCase: GetListMoviesUseCase,
) : ViewModel() {

    private val bestMovies = getMoviesListUseCase.execute("123")
    private val favMovies = getMoviesListUseCase.execute("123")

    private val mutableUiState = MutableStateFlow(ListMoviesUiState())
    val uiState: StateFlow<ListMoviesUiState> = mutableUiState.asStateFlow()

    init {
        combine(
            bestMovies,
            favMovies,
        ) { bestMovies, favMovies ->
            mutableUiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    bestMovies = WrappedMovieStateList(bestMovies.data?.movies?.map { it.toState() } ?: emptyList()),
                    favoritesMovies = WrappedMovieStateList(favMovies.data?.movies?.map { it.toState() } ?: emptyList()),
                    error = bestMovies.error ?: favMovies.error
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(7_000),
            initialValue = ListMoviesUiState()
        ).launchIn(viewModelScope)

    }

    fun getPaginationMovieList() = getMoviesListUseCase.execute("")
        .onStart { mutableUiState.value = mutableUiState.value.copy(isLoadingPagination = true) }
        .onEach { res ->
            delay(2_250)
            when (res) {
                is Resource.Success -> {
                    mutableUiState.value = uiState.value.copy(
                        bestMovies = WrappedMovieStateList(list = merge(
                            uiState.value.bestMovies.list,
                            res.data?.movies?.map { it.toState() } ?: emptyList()
                        ))
                    )
                }
                is Resource.Error -> {
                    mutableUiState.value = mutableUiState.value.copy(error = res.error)
                }
            }
        }
        .onCompletion { mutableUiState.value = mutableUiState.value.copy(isLoadingPagination = false) }
        .launchIn(viewModelScope)

    fun onClickItem(id: String) {
        if(mutableUiState.value.favoritesMovies.list.isNotEmpty()){
            mutableUiState.value = mutableUiState.value.copy(
                favoritesMovies = WrappedMovieStateList(mutableUiState.value.favoritesMovies.list.subList(0,Random().nextInt(mutableUiState.value.favoritesMovies.list.size)))
            )
        } else {
            mutableUiState.value = mutableUiState.value.copy(
                favoritesMovies = WrappedMovieStateList(emptyList())
            )
        }

    }
}

fun <T> merge(first: List<T>, second: List<T>): List<T> {
    return first + second
}