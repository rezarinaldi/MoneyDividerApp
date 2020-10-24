package com.rezarinaldi.moneydividerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rezarinaldi.moneydividerapp.databinding.ActivityMainBinding
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // activity data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // declaring view model
        viewModel = ViewModelProviders.of(this).get(MoneyViewModel::class.java)
        // as live data is used, lifecycle owner must be set
        binding.lifecycleOwner = this
        // binding view model
        binding.moneyModel = viewModel

        //observing view model public live data members. Any change is directly noticed here
        viewModel.noValueToast.observe(this, Observer {
            Toast.makeText(this, "No field should remain empty", Toast.LENGTH_SHORT).show()
        })

        viewModel.difference.observe(this , Observer {
            if(it == 0.0) {
                binding.hasil.text = getString(R.string.jumlahSama)
            } else if(it > 0) {
                binding.hasil.text = getString(R.string.jumlah2Lebih) + " " + it.toString()
            } else {
                binding.hasil.text = getString(R.string.jumlah1Lebih) + " " + it.absoluteValue.toString()
            }
        })
    }
}