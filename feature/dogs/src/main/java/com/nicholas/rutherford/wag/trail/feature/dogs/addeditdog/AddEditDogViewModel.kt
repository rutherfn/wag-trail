package com.nicholas.rutherford.wag.trail.feature.dogs.addeditdog

import androidx.lifecycle.ViewModel
import com.nicholas.rutherford.wag.trail.base.vm.BaseViewModel
import com.nicholas.rutherford.wag.trail.base.vm.ViewModelKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// todo documentation
@Inject
@ContributesIntoMap(
    scope = AppScope::class,
    binding = binding<@ViewModelKey(value = AddEditDogViewModel::class) ViewModel>()
)
class AddEditDogViewModel : BaseViewModel() {

    private val addEditDogMutableStateFlow = MutableStateFlow(value = AddEditDogState())
    val addEditDogStateFlow = addEditDogMutableStateFlow.asStateFlow()

    fun onNameChanged(name: String) = Unit
}
