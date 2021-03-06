package com.example.novaera

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.novaera.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModelCellular: ViewModelCellular by activityViewModels()

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?

): View? {
    binding = FragmentFirstBinding.inflate(inflater,container,false)
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val adapter = AdapterCellular()
    binding.rvCellular.adapter = adapter
    binding.rvCellular.layoutManager= GridLayoutManager(context,1)

    viewModelCellular.cellularLiveDataFromDB.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
        it?.let{
            Log.d("list","$it" )
            adapter.update(it)
        }
    })

    adapter.selectedItem().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
        it?.let{
            val bundle= Bundle()
            bundle.putInt("LISTA",it.id)
            bundle.putString("Title",it.name)

            viewModelCellular.getBindCellular(it.id)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }
    })

}
}
