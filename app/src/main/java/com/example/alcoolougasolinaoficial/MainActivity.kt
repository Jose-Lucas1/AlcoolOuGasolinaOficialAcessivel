package com.example.alcoolougasolinaoficial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        setContent {
            FuelCalculatorApp()
        }
    }
}

@Composable
fun FuelCalculatorApp() {
    var gasolinaPrice by remember { mutableStateOf("") }
    var alcoolPrice by remember { mutableStateOf("") }
    var isAlcoolBetter by remember { mutableStateOf(false) }
    var isSeventyFive by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Com qual combustível?",
            style = TextStyle(fontSize = 24.sp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .background(Color(0xFF00695C), shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Gasolina ou Álcool?",
                style = TextStyle(fontSize = 20.sp, color = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))

            FuelInputField(
                label = "Preço da gasolina",
                value = gasolinaPrice,
                onValueChange = { gasolinaPrice = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            FuelInputField(
                label = "Preço do álcool",
                value = alcoolPrice,
                onValueChange = { alcoolPrice = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = if (isSeventyFive) "75%" else "70%",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
                Switch(
                    checked = isSeventyFive,
                    onCheckedChange = { isSeventyFive = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFFD32F2F),
                        uncheckedThumbColor = Color.Gray,
                        checkedTrackColor = Color(0xFF80CBC4),
                        uncheckedTrackColor = Color(0xFF004D40)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val gasolina = gasolinaPrice.toFloatOrNull()
                    val alcool = alcoolPrice.toFloatOrNull()
                    val comparisonValue = if (isSeventyFive) 0.75 else 0.7

                    if (gasolina != null && alcool != null) {
                        isAlcoolBetter = alcool / gasolina <= comparisonValue
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text(text = "CALCULAR", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isAlcoolBetter) "Álcool é mais vantajoso" else "Gasolina é mais vantajosa",
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
    }
}

@Composable
fun FuelInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF004D40), shape = MaterialTheme.shapes.small)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFuelCalculatorApp() {
    FuelCalculatorApp()
}
