package com.example.novaera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.novaera.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModelCellular by activityViewModels()
    var idImage: Int = 0
    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idImage = requireArguments().getInt("LISTA")
            name = requireArguments().getString("name", "")

        }
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentSecondBinding.inflate(inflater, container, false)
            return binding.root

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            var adapter = AdapterBindCellular()
            binding.rvBind.adapter = adapter
            binding.rvBind.layoutManager = GridLayoutManager(context, 1)

            viewModel.returnBindCellular(idImage).observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.update(it)
                }
            })
        }
    }

