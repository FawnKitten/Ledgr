package com.example.database

object RegistrationUtil {

    private var existingUsers = listOf("cosmicF", "cosmicY", "bob", "alice")

    /**
     * Check if email follows the format name@subdomain.domain
     * - eg: hello@world.com test.user@email.edu
     */
    fun validateEmail(email: String): Boolean {
        val emailPattern = """^[a-z0-9._]+@[a-z]+[.][a-z]+$""".toRegex()
        return emailPattern.matches(email.lowercase())
    }

    /**
     * Check if username has at least 3 characters and does not exist
     * - allowed usernames: "usr" "user" "hello world"
     * - blocked usernames: "e" "he" "bob" "alice"
     */
    fun validateUser(username: String): Boolean {
        val userPattern = """.{3,}""".toRegex()
        return userPattern.matches(username) &&
                !existingUsers.contains(username)
    }

    /**
     * Check if password is secure
     * - has at least 8 characters
     * - has at least one digit
     * - one capital letter
     * - both passwords have to match
     */
    fun validatePassword(passwordOriginal: String, passwordConfirm: String): Boolean {
        // check if password has at least 8 characters, if not return false
        val eightCharacters = """.{8,}""".toRegex()
        if (!eightCharacters.matches(passwordOriginal)) return false
        // check if password has a digit, if not return false
        val oneDigit = """\d""".toRegex()
        if (!oneDigit.containsMatchIn(passwordOriginal)) return false
        // check if password has a capital letter
        val capitalLetter = """[A-Z]""".toRegex()
        if (!capitalLetter.containsMatchIn(passwordOriginal)) return false
        // check if passwords match
        if (passwordOriginal != passwordConfirm) return false
        // all conditions are met
        return true
    }

    /**
     * check if name isn't empty
     */
    fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }
}