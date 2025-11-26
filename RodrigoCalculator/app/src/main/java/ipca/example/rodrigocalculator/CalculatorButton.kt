package ipca.example.rodrigocalculator

import android.widget.Button
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.example.rodrigocalculator.ui.theme.Black
import ipca.example.rodrigocalculator.ui.theme.ButtonGrey
import ipca.example.rodrigocalculator.ui.theme.Grey
import ipca.example.rodrigocalculator.ui.theme.LightGrey
import ipca.example.rodrigocalculator.ui.theme.Orange
import ipca.example.rodrigocalculator.ui.theme.RodrigoCalculatorTheme


@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    label : String,
    isOperation : Boolean = false,
    onNumPressed : (String) -> Unit
){

    val buttonBackgroundColor = when (label) {
        "AC", "C" -> Orange
        "+", "-", "×", "÷", "=","√", "%" -> Grey
        else -> ButtonGrey
    }

    Button(
        modifier = modifier
            .size(90.dp)
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonBackgroundColor,
        ),
        onClick = {
            onNumPressed(label)
        }
    ) {
        Text(
            label,
            style = if ( label.count()==1)
                MaterialTheme.typography.displayMedium else
                MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview(){
    RodrigoCalculatorTheme {
        CalculatorButton(
            modifier = Modifier,
            label = "7",
            onNumPressed = {}
        )
    }
}