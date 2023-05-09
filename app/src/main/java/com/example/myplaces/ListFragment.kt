package com.example.myplaces

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myplaces.data.MyPlaces
import com.example.myplaces.databinding.FragmentListBinding
import com.example.myplaces.databinding.FragmentViewBinding
import com.example.myplaces.model.MyPlacesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    /*private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!*/
    private val myPlacesViewModel:MyPlacesViewModel by activityViewModels()
    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!
    //private val myPlacesViewModel: MyPlacesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_new_place->{
                this.findNavController().navigate(R.id.action_ListFragment_to_EditFragment)
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_my_places_list)
        item.isVisible = false;
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val myPlacesList: ListView = requireView().findViewById<ListView>(R.id.my_places_list)
        myPlacesList.adapter = ArrayAdapter<MyPlaces>(view.context, android.R.layout.simple_list_item_1, myPlacesViewModel.myPlacesList)
        myPlacesList.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var myPlace:MyPlaces = p0?.adapter?.getItem(p2) as MyPlaces
                Toast.makeText(view.context, myPlace.toString(), Toast.LENGTH_SHORT).show()
            }
        })*/
        binding.viewmyplacesNameText.text=myPlacesViewModel.selected?.name
        binding.viewmyplacesDescText.text=myPlacesViewModel.selected?.description
        binding.viewmyplacesFinishedButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}