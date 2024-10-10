package com.example.alcoholorpetrolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoholorpetrolapp.ui.theme.AlcoholOrPetrolAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcoholOrPetrolAppTheme { MainScreen() }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    AlcoholOrPetrolAppTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.width(300.dp).height(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Saiba a melhor opção para abastecimento do seu carro!",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Form()
        }
    }
}

@Composable
fun Form() {
    var alcoholPrice by remember { mutableStateOf("") }
    var petrolPrice by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = alcoholPrice,
            onValueChange = { alcoholPrice = it },
            label = { Text("Preço do Álcool") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = petrolPrice,
            onValueChange = { petrolPrice = it },
            label = { Text("Preço da Gasolina") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { result = calculateBestOption(alcoholPrice, petrolPrice) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }

        Text(text = result, fontSize = 12.sp)
    }
}

fun calculateBestOption(alcoholPrice: String, petrolPrice: String): String {
    val alcohol = alcoholPrice.toDoubleOrNull() ?: 0.0
    val petrol = petrolPrice.toDoubleOrNull() ?: 0.0

    return if (alcohol / petrol >= 0.7) "Gasolina é mais vantajosa"
    else "Álcool é mais vantajoso"
}