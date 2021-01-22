package com.example.asteroidradarapp.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asteroidradarapp.adapter.AsteroidAdapter
import com.example.asteroidradarapp.R
import com.example.asteroidradarapp.databinding.FragmentAsteroidListBinding
import com.example.asteroidradarapp.domain.Asteroid
import com.example.asteroidradarapp.viewmodel.AsteroidFilter
import com.example.asteroidradarapp.viewmodel.AsteroidListViewModel
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

/**
 * A fragment that represents a list of asteroids.
 */
class AsteroidListFragment : Fragment() {


    private lateinit var binding: FragmentAsteroidListBinding

    /**
     * Lazily initialize our [AsteroidListViewModel].
     */
    private val viewModel: AsteroidListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, AsteroidListViewModel.Factory(activity.application)).get(
            AsteroidListViewModel::class.java
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the AsteroidListFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Data binding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_asteroid_list, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the AsteroidListViewModel
        binding.asteroidListViewModel = viewModel

        /*
         * Sets the adapter of the Asteroid RecyclerView with clickHandler lambda that
         * tells the viewModel when our property is clicked
         */
        binding.asteroidRecycler.adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            viewModel.displayAsteroidDetails(it)
        })

        /*
         * Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
         * After navigating, call displayAsteroidDetailsComplete() so that the ViewModel is ready
         * for another navigation event.
         * */
        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController()
                    .navigate(AsteroidListFragmentDirections.actionShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayAsteroidDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }


    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all_asteroids -> viewModel.setAsteroidFilter(AsteroidFilter.ALL_ASTEROIDS)
            R.id.filter_asteroids -> {
                showPicker()
            }
        }

        return true
    }

    private fun showPicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                var month2 = (month + 1).toString()
                var day2 = day.toString()

                if (month < 10) {
                    month2 = "0$month2"
                }
                if (day < 10) {
                    day2 = "0$day2"
                }
                val selectedDate = "" + year + "-" + (month2) + "-" + day2
                Log.d("selected", selectedDate)
                viewModel.updateFilter(selectedDate)
            },
            year,
            month,
            day
        )


        dpd.show()
    }
}