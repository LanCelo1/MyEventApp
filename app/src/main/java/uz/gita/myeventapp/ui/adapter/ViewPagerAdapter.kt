package uz.gita.myeventapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.myeventapp.R
import uz.gita.myeventapp.data.model.StateData

class ViewPagerAdapter(
    val states : List<StateData>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.screen_main,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        return holder.bind()
    }

    override fun getItemCount(): Int {
        return states.size
    }
}