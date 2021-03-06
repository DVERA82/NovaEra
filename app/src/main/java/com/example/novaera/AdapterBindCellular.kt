package com.example.novaera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novaera.databinding.ItemBindBinding

class AdapterBindCellular : RecyclerView.Adapter<AdapterBindCellular.BindVH>() {

    private var listBindCellular = listOf<BindCellular>()

    private var selectedItemBindCellular = MutableLiveData<BindCellular>()

    fun selectedItemBindCellular() = selectedItemBindCellular

    fun update(list: List<BindCellular>) {

        listBindCellular= list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindVH {
        return BindVH( ItemBindBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterBindCellular.BindVH, position: Int) {
        val CellularDataClass = listBindCellular[position]
        holder.bind(CellularDataClass)
    }

    override fun getItemCount(): Int = listBindCellular.size


    inner class BindVH(private val binding: ItemBindBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(bindCellular: BindCellular) {
            Glide.with(binding.ivImage2)
                .load(bindCellular.image)
                .into(binding.ivImage2)

            binding.tvNameB.text = bindCellular.name
            binding.tvPriceB.text = bindCellular.price.toString()
            binding.tvLastPriceB.text = bindCellular.lastPrice.toString()
            binding.tvDescription.text = bindCellular.description

            if (bindCellular.credit) {
                binding.tvCredit.text = "ACEPTA CRÉDITO"
            }

        }

        override fun onLongClick(v: View?): Boolean {
            selectedItemBindCellular.value = listBindCellular[adapterPosition]
            return true
        }
    }
}