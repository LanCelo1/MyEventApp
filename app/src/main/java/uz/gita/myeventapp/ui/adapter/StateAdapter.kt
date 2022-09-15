package uz.gita.myeventapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.myeventapp.data.model.StateData
import uz.gita.myeventapp.databinding.ItemLayoutBinding

class StateAdapter(var list : List<StateData>) : RecyclerView.Adapter<StateAdapter.VH>() {
    private var itemClickListener : ((Int,Boolean)->Unit)? = null
    inner class VH(var binding : ItemLayoutBinding) :  RecyclerView.ViewHolder(binding.root){

        init {
            binding.switchState.setOnCheckedChangeListener { buttonView, isChecked ->
                itemClickListener?.invoke(absoluteAdapterPosition,isChecked)
            }
        }
        fun bind() = with(binding){
            val item = list[absoluteAdapterPosition]
            switchState.setText(item.name)
            switchState.isChecked = item.state
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.bind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(block : ((Int,Boolean)->Unit)){
        itemClickListener = block
    }
}