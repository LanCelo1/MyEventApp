package uz.gita.myeventapp.ui.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myeventapp.data.local.MySharedPreferences
import uz.gita.myeventapp.data.model.StateData
import uz.gita.myeventapp.domain.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor() : ViewModel(), MainViewModel {
    var repository = MainRepository.getInstance()
    override var listStates: List<StateData> = repository.getState()
    override val startServiceListener = MutableLiveData<StateData>()

    override fun startService(position: Int, isChecked: Boolean) {
//        startServiceListener.value = Pair(listStates[position], isChecked)
        startServiceListener.value = listStates[position]
        repository.changeState(position, isChecked)
    }

    override fun saveState() {
//        repository.saveStates(list)
    }
}