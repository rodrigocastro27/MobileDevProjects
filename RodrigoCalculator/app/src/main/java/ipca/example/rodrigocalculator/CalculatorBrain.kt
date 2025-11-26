package ipca.example.rodrigocalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class CalculatorBrain {

    enum class Operation(val op: String) {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("×"),
        DIVIDE("÷"),
        EQUAL("="),
        SQRT("√"),
        PERCENTAGE("%"),
        CLEAR("AC"),
        CLEAR_ENTRY("C");


        companion object {
            fun parseOperation(op: String): Operation {
                return when (op) {
                    "+" -> ADD
                    "-" -> SUBTRACT
                    "×" -> MULTIPLY
                    "÷" -> DIVIDE
                    "=" -> EQUAL
                    "√" -> SQRT
                    "%" -> PERCENTAGE
                    "AC" -> CLEAR
                    "C" -> CLEAR_ENTRY
                    else -> EQUAL
                }
            }
        }
    }

    var  operand = 0.0
    var  operation : Operation? = null


    fun unaryOperation(newOperand : Double, newOperation : Operation) {
        var result = newOperand
        when(newOperation){
            Operation.SQRT -> result = kotlin.math.sqrt(newOperand)
            Operation.PERCENTAGE -> result = newOperand / 100

            Operation.EQUAL -> {
                if (operation != null) {
                    when(operation){
                        Operation.ADD ->  { result = operand + newOperand }
                        Operation.SUBTRACT -> result = operand - newOperand
                        Operation.MULTIPLY -> result = operand * newOperand
                        Operation.DIVIDE -> {
                            if (newOperand == 0.0) {
                                result = Double.NaN
                            } else {
                                result = operand / newOperand
                            }
                        }
                        else -> { result = newOperand }
                    }
                }
                operation = null
            }

            Operation.CLEAR -> {
                operation = null
                result = 0.0
            }


            Operation.CLEAR_ENTRY -> {
                result = 0.0
            }
            else -> {}
        }
        operand = result
    }


    fun doOperation(newOperand : Double, newOperation : Operation) {


        if (operation != null) {
            var calculatedResult: Double = newOperand


            when(operation){
                Operation.ADD ->  { calculatedResult = operand + newOperand }
                Operation.SUBTRACT -> calculatedResult = operand - newOperand
                Operation.MULTIPLY -> calculatedResult = operand * newOperand
                Operation.DIVIDE -> {
                    if (newOperand == 0.0) {
                        calculatedResult = Double.NaN
                    } else {
                        calculatedResult = operand / newOperand
                    }
                }
                else -> {  }
            }

            operand = calculatedResult
        } else {

            operand = newOperand
        }


        operation = newOperation
    }
}