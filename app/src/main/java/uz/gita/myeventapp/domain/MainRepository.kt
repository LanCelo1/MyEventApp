package uz.gita.myeventapp.domain

import uz.gita.myeventapp.data.local.MySharedPreferences
import uz.gita.myeventapp.data.model.StateData

class MainRepository {
    companion object{
        private var instance : MainRepository? = null

        fun getInstance() : MainRepository{
            if (instance == null){
                instance = MainRepository()
            }
            return instance!!
        }
    }

    private val list = listOf<StateData>(
        StateData("Airplane mode",false),
        StateData("Screen off",false),
        StateData("Screen on",false),
        StateData("Power connected",false),
        StateData("Power disconnected",false),
        StateData("Battery ok",false),
        StateData("Battery low",false),
    )

    fun saveStates(list: List<StateData>){
        list.forEachIndexed { index, stateData ->
            MySharedPreferences.putBoolean(index.toString(),stateData.state)
        }
    }

    fun getState() : List<StateData>{
        for (i in 0 until list.size){
            list[i].state = MySharedPreferences.getBoolean(i.toString(),false)
        }
        return list
    }

    fun changeState(position: Int, checked: Boolean) {
        MySharedPreferences.putBoolean(position.toString(),checked)
    }
}