package com.example.asteroidradarapp.view

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.asteroidradarapp.viewmodel.DetailAsteroidViewModel
import com.example.asteroidradarapp.R
import com.example.asteroidradarapp.databinding.FragmentDetailAsteroidBinding


class DetailAsteroidFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailAsteroidBinding.inflate(inflater)

        val asteroid = DetailAsteroidFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        val viewModelFactory = DetailAsteroidViewModel.Factory(asteroid, application)


        binding.detailViewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailAsteroidViewModel::class.java)

        binding.helpButton.setOnClickListener{
            showAlert()
        }

        return binding.root
    }

    private fun showAlert() {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        // set message of alert dialog
        dialogBuilder.setMessage(R.string.astronomica_unit_explanation)
            // if the dialog is cancelable
            .setCancelable(true)
            // positive button text and action
            .setPositiveButton(R.string.ok, null)




        // create dialog box
        val alert = dialogBuilder.create()

        // show alert dialog
        alert.show()
        alert.getButton(AlertDialog.BUTTON_POSITIVE).contentDescription = getString(R.string.ok)

    }


}