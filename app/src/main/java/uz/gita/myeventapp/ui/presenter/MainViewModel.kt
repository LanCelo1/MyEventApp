package uz.gita.myeventapp.ui.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.myeventapp.data.model.StateData

interface MainViewModel {
    var listStates : List<StateData>
    val startServiceListener: LiveData<StateData>
    fun startService(position: Int, isChecked: Boolean)
    fun saveState()
}