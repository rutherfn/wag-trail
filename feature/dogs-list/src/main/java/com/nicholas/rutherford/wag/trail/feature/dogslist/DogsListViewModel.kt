package com.nicholas.rutherford.wag.trail.feature.dogslist

import androidx.lifecycle.ViewModel
import com.nicholas.rutherford.wag.trail.base.vm.BaseViewModel
import com.nicholas.rutherford.wag.trail.base.vm.ViewModelKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Inject
@ContributesIntoMap(
    scope = AppScope::class,
    binding = binding<@ViewModelKey(value = DogsListViewModel::class) ViewModel>(),
)
class DogsListViewModel : BaseViewModel() {

    private val dogsListMutableStateFlow = MutableStateFlow(DogsListState())

    val dogsListStateFlow = dogsListMutableStateFlow.asStateFlow()

    fun onAddDogClicked() = Unit
}
