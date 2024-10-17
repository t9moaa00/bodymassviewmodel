package com.example.bodymassviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bodymassviewmodel.BMICalculatorViewModel
import com.example.bodymassviewmodel.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {

    // Initialize the ViewModel
    private val bmiViewModel: BMICalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // Call the UI function
                BMICalculatorScreen(bmiViewModel)
            }
        }
    }
}

@Composable
fun BMICalculatorScreen(viewModel: BMICalculatorViewModel) {
    // Collecting state variables
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Title
        Text(
            text = "Body Mass Index",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Height Input
        OutlinedTextField(
            value = height,
            onValueChange = {
                height = it
                viewModel.updateHeight(it)  // Update the ViewModel
            },
            label = { Text("Height (meters)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Weight Input
        OutlinedTextField(
            value = weight,
            onValueChange = {
                weight = it
                viewModel.updateWeight(it)  // Update the ViewModel
            },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        // Display the BMI result
        Text(
            text = if (viewModel.bmi.isNotEmpty()) "Body Mass Index is ${viewModel.bmi}" else "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BMIPreview() {
    BMICalculatorTheme {
        BMICalculatorScreen(BMICalculatorViewModel())
    }
}
