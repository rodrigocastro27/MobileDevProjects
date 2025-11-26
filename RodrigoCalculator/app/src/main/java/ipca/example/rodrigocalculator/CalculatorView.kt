package ipca.example.rodrigocalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.example.rodrigocalculator.ui.theme.Black
import ipca.example.rodrigocalculator.ui.theme.LightGrey
import ipca.example.rodrigocalculator.ui.theme.RodrigoCalculatorTheme

@Composable
fun CalculatorView(
    modifier: Modifier = Modifier
){

    var displayText by remember { mutableStateOf("0") }
    val calculatorBrain by remember {  mutableStateOf(CalculatorBrain()) }
    var userIsTypingNumber by remember { mutableStateOf(true) }

    val onDigitPressed : (String) -> Unit = { digit ->
        if (userIsTypingNumber) {
            if (digit == ".") {
                if (!displayText.contains('.')) {
                    displayText += digit
                }
            } else {
                if (displayText == "0" || displayText == "Error") {
                    displayText = digit
                } else {
                    displayText += digit
                }
            }
        }else{
            if (digit == ".") {
                displayText = "0."
            }else {
                displayText = digit
            }
        }
        userIsTypingNumber = true
    }


    val onOperationPressed : (String) -> Unit = { op ->

        val operationEnum = CalculatorBrain.Operation.parseOperation(op)
        val valueOnDisplay = displayText.toDoubleOrNull() ?: 0.0

        
        val isBinaryOp = (operationEnum == CalculatorBrain.Operation.ADD ||
                operationEnum == CalculatorBrain.Operation.SUBTRACT ||
                operationEnum == CalculatorBrain.Operation.MULTIPLY ||
                operationEnum == CalculatorBrain.Operation.DIVIDE)


        if (operationEnum == CalculatorBrain.Operation.CLEAR_ENTRY) {
            displayText = "0"
            userIsTypingNumber = true
        } else {


            if (isBinaryOp) {


                if (calculatorBrain.operation != null) {

                    calculatorBrain.operation = operationEnum
                } else {

                    calculatorBrain.doOperation(valueOnDisplay, operationEnum)
                }
            }


            else {
                calculatorBrain.unaryOperation(valueOnDisplay, operationEnum)
            }



            val result = calculatorBrain.operand


            if (!isBinaryOp) {

                if (result.isNaN()) {
                    displayText = "Error"
                    calculatorBrain.operand = 0.0
                    calculatorBrain.operation = null
                }
                else if ((result % 1.0) == 0.0 && result.isFinite()) {
                    displayText = result.toLong().toString()
                } else {
                    displayText = result.toString()
                }
            }


            userIsTypingNumber = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(LightGrey)
                .border(2.dp, Black),
            text = displayText,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displayLarge
        )
        Row() {
            CalculatorButton( label = "AC" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
            CalculatorButton( label = "C" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
            CalculatorButton( label = "√" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
            CalculatorButton( label = "%" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
        }
        Row() {
            CalculatorButton( label = "7" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "8" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "9" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "+" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
        }
        Row() {
            CalculatorButton( label = "6" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "5" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "4" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "-" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
        }
        Row() {
            CalculatorButton( label = "1" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "2" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "3" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "÷" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
        }
        Row() {
            CalculatorButton( label = "0" , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "." , onNumPressed =  onDigitPressed )
            CalculatorButton( label = "=" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
            CalculatorButton( label = "×" ,
                onNumPressed =  onOperationPressed,
                isOperation = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorViewPreview(){
    RodrigoCalculatorTheme {
        CalculatorView()
    }
}