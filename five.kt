import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main() {
    val filePath = "contacts.txt"
    val contacts = readContactsFromFile(filePath).toMutableList()

    while (true) {
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

        val userResp = readIntInput()

        when (userResp) {
            1 -> addContact(filePath, contacts)
            2 -> removeContact(filePath, contacts)
            3 -> editContact(filePath, contacts)
            4 -> viewContacts(contacts)
            5 -> {
                println("\nExiting ...")
                exitProcess(0)
            }
            else -> println("\nInvalid function number, exiting ...")
        }

        // add a pause
        println("\nPress Enter to continue ...")
        readlnOrNull()
    }
}

fun addContact(filePath: String, contacts: MutableList<Contact>) {
    val name = readStringInput("Enter the name of the contact: ")
    val number = readStringInput("Enter the number of the contact: ")

    if (!isValidPhoneNumber(number)) {
        println("\nInvalid phone number, exiting ...")
        return
    }

    val contact = Contact(name, number)
    contacts.add(contact)

    if (overwriteContactsToFile(filePath, contacts)) {
        println("\nContact added successfully")
    } else {
        println("\nError adding contact, exiting ...")
    }
}

fun removeContact(filePath: String, contacts: MutableList<Contact>) {
    val name = readStringInput("Enter the name of the contact to remove: ")

    val contact = contacts.find { it.name == name }
    if (contact != null) {
        contacts.remove(contact)
        if (overwriteContactsToFile(filePath, contacts)) {
            println("\nContact removed successfully")
        } else {
            println("\nError removing contact, exiting ...")
        }
    } else {
        println("\nContact not found, exiting ...")
    }
}

fun editContact(filePath: String, contacts: MutableList<Contact>) {
    val name = readStringInput("Enter the name of the contact to edit: ")

    val contact = contacts.find { it.name == name }
    if (contact == null) {
        println("\nContact not found, exiting ...")
        return
    }

    val newName = readStringInput("Enter the new name of the contact: ")
    val newNumber = readStringInput("Enter the new number of the contact: ")

    if (!isValidPhoneNumber(newNumber)) {
        println("\nInvalid phone number, exiting ...")
        return
    }

    val updatedContact = Contact(newName, newNumber)
    contacts[contacts.indexOf(contact)] = updatedContact

    if (overwriteContactsToFile(filePath, contacts)) {
        println("\nContact edited successfully")
    } else {
        println("\nError editing contact, exiting ...")
    }
}

fun viewContacts(contacts: List<Contact>) {
    if (contacts.isEmpty()) {
        println("\nNo contacts found")
    } else {
        println("\nContacts:")
        contacts.forEach { println(it) }
    }
}

fun readContactsFromFile(filePath: String): List<Contact> {
    try {
        val path = Paths.get(filePath)
        return if (Files.exists(path) && Files.size(path) > 0) {
            Files.readAllLines(path).mapNotNull { line ->
                val parts = line.split(" - ")
                if (parts.size == 2) Contact(parts[0], parts[1]) else null
            }
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        println("\nError reading contacts from file: ${e.message}")
        return emptyList()
    }
}

fun overwriteContactsToFile(filePath: String, contacts: List<Contact>): Boolean {
    return try {
        val path = Paths.get(filePath)
        Files.write(path, contacts.joinToString("\n") { it.toString() }.toByteArray())
        true
    } catch (e: Exception) {
        println("\nError writing contacts to file: ${e.message}")
        false
    }
}

fun isValidPhoneNumber(number: String): Boolean {
    return number.all { it.isDigit() }
}

fun readStringInput(prompt: String): String {
    print(prompt)
    return readlnOrNull() ?: ""
}

fun readIntInput(): Int {
    val input = readStringInput("Function: ")
    return input.toIntOrNull() ?: -1
}

data class Contact(val name: String, val number: String) {
    init {
        if (name.isBlank() || number.isBlank()) {
            throw IllegalArgumentException("Name and number cannot be empty")
        }
    }

    override fun toString(): String = "$name - $number"
}
