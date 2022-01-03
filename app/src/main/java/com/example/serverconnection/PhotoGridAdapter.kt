package com.example.serverconnection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.serverconnection.databinding.GrideViewItemBinding
import com.example.serverconnection.network.MarsProperty


class PhotoGridAdapter(private val onClickListenerProperty: OnClickListenerProperty) :
    ListAdapter<MarsProperty, PhotoGridAdapter.MarsAdapterViewmodel>(DiffCallback){
    class MarsAdapterViewmodel(val binding:GrideViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty){
            binding.viewmodel=marsProperty
            binding.executePendingBindings()
        }
    }

    object DiffCallback: DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsAdapterViewmodel {
        return MarsAdapterViewmodel(GrideViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsAdapterViewmodel, position: Int) {
        val marsList=getItem(position)
        holder.itemView.setOnClickListener {
            onClickListenerProperty.onClick(marsList)
        }
        holder.bind(marsList)
    }
    class OnClickListenerProperty( val clicklistener: (marsproperty:MarsProperty)-> Unit){
        fun onClick(marsProperty: MarsProperty)=clicklistener(marsProperty)
    }

}
