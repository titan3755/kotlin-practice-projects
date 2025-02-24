fun main() {
    println("Temperature conversion")
    print("Enter the value: ")
    val usrValInputS = readlnOrNull()
    if (usrValInputS == null || usrValInputS.toDoubleOrNull() == null) {
        println("Invalid input")
        return
    }
    val usrValInput = usrValInputS.toDouble()
    println()
    print("Convert from? (C/F/K): ")
    var usrConvInitInput = readlnOrNull()
    print("Convert to? (C/F/K): ")
    var usrConvInput = readlnOrNull()
    if (usrConvInput == null) {
        println("Invalid input")
        return
    }
    if (usrConvInitInput == null) {
        print("Invalid input")
        return
    }
    usrConvInitInput = usrConvInitInput.lowercase()
    usrConvInput = usrConvInput.lowercase()
    val result = when (usrConvInput) {
        "c" -> {
            if (usrConvInitInput == "c") {
                usrValInput
            } else if (usrConvInitInput == "f") {
                (usrValInput - 32.0) * (5.0/9.0)
            } else if (usrConvInitInput == "k") {
                (usrValInput - 273.15)
            } else {
                usrValInput
            }
        }
        "f" -> {
            if (usrConvInitInput == "c") {
                (usrValInput * (9.0/5.0)) + 32.0
            } else if (usrConvInitInput == "f") {
                usrValInput
            } else if (usrConvInitInput == "k") {
                (((usrValInput - 273.15) * (9.0/5.0)) + 32.0)
            } else {
                usrValInput
            }
        }
        "k" -> {
            if (usrConvInitInput == "c") {
                usrValInput + 273.15
            } else if (usrConvInitInput == "f") {
                (usrValInput - 32.0) * (5.0/9.0) + 273.15
            } else if (usrConvInitInput == "k") {
                usrValInput
            } else {
                usrValInput
            }
        }
        else -> usrValInput
    }
    println("Result: $result")
}