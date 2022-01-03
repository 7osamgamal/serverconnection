package com.example.serverconnection.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.serverconnection.R
import com.example.serverconnection.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val marsproperty= DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewmodelfactory=DetailViewModelFactory(marsproperty,application)
        binding.viewmodel= ViewModelProvider(this,viewmodelfactory).get(DetailViewModel::class.java)
        return binding.root
    }
}
