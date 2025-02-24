fun main() {
    println("Basic Calculator")
    println("Enter basic math expression (using +, -, *, /, (, ), and numbers): ")
    val input = readlnOrNull()
    val result = calculate(input)
    println("Result: $result")
}

fun calculate(input: String?): Any {
    if (input == null) {
        return 0.0
    }
    // Remove spaces from the input
    val expr = input.replace(" ", "")

    // Process parentheses first
    val processed = processParentheses(expr)

    // Now process the final expression (without parentheses)
    return evaluateExpression(processed)
}

fun processParentheses(expr: String): String {
    var result = expr
    var openIndex: Int
    var closeIndex: Int

    // Repeat until no parentheses are left
    while (result.contains("(")) {
        openIndex = result.lastIndexOf("(")
        closeIndex = result.indexOf(")", openIndex)

        // Extract the subexpression inside the parentheses
        val subExpression = result.substring(openIndex + 1, closeIndex)

        // Evaluate the subexpression and replace it with the result
        val evaluatedSubExpr = evaluateExpression(subExpression)
        result = result.substring(0, openIndex) + evaluatedSubExpr + result.substring(closeIndex + 1)
    }

    return result
}

fun evaluateExpression(expr: String): String {
    var result = expr

    // First handle multiplication and division
    result = evaluateOperations(result, listOf("*", "/"))

    // Then handle addition and subtraction
    return evaluateOperations(result, listOf("+", "-"))
}

fun evaluateOperations(expr: String, operators: List<String>): String {
    var tempExpr = expr

    // Loop through each operator
    for (operator in operators) {
        while (tempExpr.contains(operator)) {
            val regex = Regex("""\d+(\.\d+)?\s*\Q$operator\E\s*\d+(\.\d+)?""")
            val match = regex.find(tempExpr)

            match?.let {
                val fullMatch = it.value
                val operands = fullMatch.split(operator)
                val operand1 = operands[0].trim().toDouble()
                val operand2 = operands[1].trim().toDouble()

                val operationResult = when (operator) {
                    "*" -> operand1 * operand2
                    "/" -> operand1 / operand2
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    else -> throw IllegalArgumentException("Invalid operator")
                }

                // Replace the operation with its result
                tempExpr = tempExpr.replace(fullMatch, operationResult.toString())
            }
        }
    }

    return tempExpr
}
