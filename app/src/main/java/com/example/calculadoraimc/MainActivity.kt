package com.example.calculadoraimc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.calculo.CalcularImc
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import com.example.calculadoraimc.ui.theme.DARK_BLUE
import com.example.calculadoraimc.ui.theme.LIGHT_BLUE
import com.example.calculadoraimc.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMCTheme {
                CalculadoraIMC()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraIMC() {
    val context = LocalContext.current
    val calcularIMC = CalcularImc()

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var textoResultado by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Calculadora IMC", color = WHITE)
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = LIGHT_BLUE
            )
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp, 38.dp, 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calcular de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_BLUE
            )

            OutlinedTextField(value = peso,
                onValueChange = { it ->
                    peso = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 16.dp, 0.dp, 0.dp),
                label = {
                    Text(text = "Peso (Kg)")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                ),
                textStyle = TextStyle(DARK_BLUE, 16.sp),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(value = altura,
                onValueChange = { it ->
                    altura = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 16.dp, 0.dp, 0.dp),
                label = {
                    Text(text = "Altura")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                ),
                textStyle = TextStyle(DARK_BLUE, 16.sp),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(
                onClick = {
                    if (peso.isEmpty() || altura.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        calcularIMC.calcularImc(peso, altura)
                        textoResultado = calcularIMC.resultadoIMC()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 24.dp, 0.dp, 24.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LIGHT_BLUE
                )
            ) {
                Text(
                    text = "Calcular",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = textoResultado,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DARK_BLUE
            )
        }
    }
}
