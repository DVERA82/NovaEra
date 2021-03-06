package com.example.novaera



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novaera.databinding.ItemCelularBinding


class AdapterCellular : RecyclerView.Adapter<AdapterCellular.CellularVH> () {

    private var listAdapterCellular = listOf<Cellular>()

    private var selectedItem = MutableLiveData<Cellular>()

    fun selectedItem(): LiveData<Cellular> = selectedItem

    fun update(list: List<Cellular>) {

        listAdapterCellular= list
        notifyDataSetChanged()
    }

    inner class CellularVH(private val binding: ItemCelularBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(cellular: Cellular) {
            Glide.with(binding.ivImage)
                .load(cellular.image)
                .into(binding.ivImage)
            binding.tvName.text = cellular.name
            binding.tvPrice.text = cellular.price.toString()

            Log.d("list cellular", "${cellular.id}")
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            selectedItem.value = listAdapterCellular[adapterPosition]

        }

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCellular.CellularVH {
            return CellularVH(ItemCelularBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: AdapterCellular.CellularVH, position: Int) {
            val DataClassCellular = listAdapterCellular[position]
            holder.bind(DataClassCellular)
        }

        override fun getItemCount(): Int = listAdapterCellular.size

}


