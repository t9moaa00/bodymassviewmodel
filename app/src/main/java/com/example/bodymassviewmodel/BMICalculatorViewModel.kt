package com.example.bodymassviewmodel

import androidx.lifecycle.ViewModel
import kotlin.math.pow

class BMICalculatorViewModel : ViewModel() {

    // Member variables for height, weight, and BMI result
    private var _height: String = ""
    var height: String
        get() = _height
        private set(value) {
            _height = value
            calculateBMI()  // Recalculate BMI whenever height changes
        }

    private var _weight: String = ""
    var weight: String
        get() = _weight
        private set(value) {
            _weight = value
            calculateBMI()  // Recalculate BMI whenever weight changes
        }

    var bmi: String = ""
        private set

    // Update methods for height and weight
    fun updateHeight(newHeight: String) {
        height = newHeight
    }

    fun updateWeight(newWeight: String) {
        weight = newWeight
    }

    // Private method for BMI calculation
    private fun calculateBMI() {
        val heightInMeters = height.toDoubleOrNull()
        val weightInKg = weight.toDoubleOrNull()

        if (heightInMeters != null && weightInKg != null && heightInMeters > 0) {
            val bmiValue = weightInKg / heightInMeters.pow(2)
            bmi = String.format("%.2f", bmiValue)
        } else {
            bmi = ""  // Reset BMI if input is invalid
        }
    }
}
