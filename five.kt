import java.nio.file.Files
import java.nio.file.Paths
import java.io.File

fun main() {
    println("-- Contacts List --")
    println("Welcome to contact list app")
    println()
    println("List of available functions -->")
    println("1. Add contact")
    println("2. Remove contact")
    println("3. Edit contact")
    println("4. View contacts")
    println("5. Exit")
    println("-- Enter the sl number associated with a function --")
    print("\n")
    print("Function: ")
    val userResp = readlnOrNull()
    if (userResp == null || userResp.toIntOrNull() == null) {
        println("\nThe provided input is not valid, exiting ...")
        return
    }
    // read file
    val filePath = "contacts.txt"
    val contacts = readContactsFromFile(filePath)
    if (contacts == null) {
        println("\nError reading contacts from file, creating new file ...")
    }


}

fun readContactsFromFile(filePath: String): String? {
    try {
        val path = Paths.get(filePath)
        return if (Files.exists(path) && Files.size(path) > 0) {
            String(Files.readAllBytes(path))
        } else {
            null
        }
    } catch(_: Exception) {
        return null
    }
}

fun overwriteContactsToFile(filePath: String, content: String): Boolean {
    try {
        val path = Paths.get(filePath)
        Files.write(path, content.toByteArray())
        return true
    } catch (_: Exception) {
        return false
    }
}

class Contact(val name: String, val number: String) {}

