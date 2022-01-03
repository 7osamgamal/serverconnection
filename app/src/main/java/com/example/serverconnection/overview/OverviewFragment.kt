package com.example.serverconnection.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.serverconnection.PhotoGridAdapter
import com.example.serverconnection.R
import com.example.serverconnection.databinding.FragmentOverviewBinding
import com.example.serverconnection.network.MarsApiFilter

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.photosGrid.adapter= PhotoGridAdapter(PhotoGridAdapter.OnClickListenerProperty{
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigate_to_selected_property.observe(viewLifecycleOwner, Observer {
            if (null!=it){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }

        })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updatefilter(when(item.itemId)
        {
            R.id.show_buy_menu-> MarsApiFilter.SHOW_BUY
            R.id.show_rent_menu->MarsApiFilter.SHOW_RENT
            else->MarsApiFilter.SHOW_ALL
        }
        )
        return true
    }
}