fun main() {
    println("-- Contacts List --")
    println("Welcome to contact list app")
    println()
    println("List of available functions -->")
    println("1. Add contact")
    println("2. Remove contact")
    println("3. Edit contact")
    println("4. Exit")
    println("-- Enter the sl number associated with a function --")
    print("\n")
    print("Function: ")
    val userResp = readlnOrNull()
    if (userResp == null || userResp.toIntOrNull() == null) {
        println("\nThe provided input is not valid, exiting ...")
        return
    }

}

