package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isSwitched = false
    private val millilitersToFluidOunces = 0.033814
    private val fluidOuncesToMilliliters = 29.5735


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.calculateButton.setOnClickListener{ calculateConversion() }

        binding.switchButton.setOnClickListener{ switchMeasurement() }
    }

    private fun calculateConversion() {
        if (isSwitched) {
            val stringInTextField = binding.fluidId.text.toString()
            val fluidOunceValue = stringInTextField.toDoubleOrNull()
            if (fluidOunceValue == null) {
                binding.millilitersId.text = Editable.Factory.getInstance().newEditable("")
                return
            }

            if (fluidOunceValue !== null) {
                val milliliters = fluidOunceValue * fluidOuncesToMilliliters
                val millilitersText = milliliters.toString()
                binding.millilitersId.text = Editable.Factory.getInstance().newEditable(millilitersText)
            } else {
                binding.fluidId.text = Editable.Factory.getInstance().newEditable("Please enter a valid number of fluid ounces.")
            }
        } else {
            val stringInTextField2 = binding.millilitersId.text.toString()
            val millilitersValue = stringInTextField2.toDoubleOrNull()
            if (millilitersValue == null) {
                binding.fluidId.text = Editable.Factory.getInstance().newEditable("")
                return
            }

            if (millilitersValue != null) {
                val fluidOunces = millilitersValue * millilitersToFluidOunces
                val fluidOuncesText = fluidOunces.toString()
                binding.fluidId.text = Editable.Factory.getInstance().newEditable(fluidOuncesText)
            } else {
                binding.fluidId.text = Editable.Factory.getInstance().newEditable("Please enter a valid number of milliliters.")
            }


        }
    }

    private fun switchMeasurement() {
        val arrowImage = binding.convertArrow
        if (isSwitched) {
            // If already mirrored, set it back to its original state
            arrowImage.scaleX = 1f
        } else {
            // If not mirrored, mirror the image
            arrowImage.scaleX = -1f
        }
        // Toggle the state for the next time the function is called
        isSwitched = !isSwitched
    }
}