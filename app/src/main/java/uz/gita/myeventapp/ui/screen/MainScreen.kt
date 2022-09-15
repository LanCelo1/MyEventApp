package uz.gita.myeventapp.ui.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Observable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myeventapp.R
import uz.gita.myeventapp.data.model.StateData
import uz.gita.myeventapp.databinding.ScreenMainBinding
import uz.gita.myeventapp.service.MyService
import uz.gita.myeventapp.ui.adapter.StateAdapter
import uz.gita.myeventapp.ui.presenter.MainViewModel
import uz.gita.myeventapp.ui.presenter.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    val binding : ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    val viewModel : MainViewModel by viewModels<MainViewModelImpl>()
    lateinit var rvAdapter : StateAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        setUpObervers()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun setUpObervers() {
        viewModel.startServiceListener.observe(this,startServiceObserver)
    }

    private fun setUpRecyclerView() {
        binding.apply {
            recyclerView.apply {
                rvAdapter = StateAdapter(viewModel.listStates)
                adapter = rvAdapter.apply {
                    setOnItemClickListener { position, isChecked ->
                        viewModel.startService(position, isChecked)
                    }
                }
                layoutManager= LinearLayoutManager(this@MainScreen.requireContext())
            }
        }
    }

    private val startServiceObserver = Observer<StateData>{
        val intent = Intent(requireContext(), MyService::class.java)
        intent.putExtra("STATE_NAME",it.name)
        intent.putExtra("STATE_VALUE",it.state)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().startForegroundService(intent)
        else requireActivity().startService(intent)
    }

    private fun startService() {

    }

    override fun onPause() {
        super.onPause()
        viewModel.saveState()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}