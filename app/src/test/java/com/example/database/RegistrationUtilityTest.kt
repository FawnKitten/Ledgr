package com.example.database

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilityTest {
    // methodName_someCondition_expectedResult
    
    @Test
    fun validatePassword_emptyPassword_isFalse() {
        val actual = RegistrationUtil.validatePassword("", "")
        // assertThat(actualValue).isEqual(desiredValue)
        assertThat(actual).isFalse()
    }
    
    @Test
    fun validatePassword_passwordsDontMatch_isFalse() {
        val actual = RegistrationUtil.validatePassword("A1bcdefgh", 
            "1Abcdefgh")
        assertThat(actual).isFalse()
    }
    
    @Test
    fun validatePassword_passwordLessThan8Characters_ifFalse() {
        val actual = RegistrationUtil.validatePassword("6Chars",
            "6Chars")
        assertThat(actual).isFalse()
    }
    
    @Test
    fun validatePassword_doesNotContainDigit_isFalse() {
        val actual = RegistrationUtil.validatePassword("NoDigits",
            "NoDigits")
        assertThat(actual).isFalse()
    }
    
    @Test
    fun validatePassword_doesNotContainCapital_isFalse() {
        val actual = RegistrationUtil.validatePassword("n0c4pitals",
            "n0c4pitals")
        assertThat(actual).isFalse()
    }
    
    @Test
    fun validatePassword_correctPassword_isTrue() {
        val actual = RegistrationUtil.validatePassword("Str0ngP4ssword",
            "Str0ngP4ssword")
        assertThat(actual).isTrue()
    }
    
    @Test
    fun validateEmail_correctEmail_isTrue() {
        assertThat(RegistrationUtil.validateEmail("joao.pollo07@icloud.com"))
            .isTrue()
    }
    
    @Test
    fun validateEmail_noDomain_isFalse() {
        assertThat(RegistrationUtil.validateEmail("joao.pollo07@icloud"))
            .isFalse()
    }

    @Test
    fun validateEmail_noAtSymbol_isFalse() {
        assertThat(RegistrationUtil.validateEmail("joao.pollo07icloud.com"))
            .isFalse()
    }

    @Test
    fun validateEmail_nothingBeforeAtSymbol_isFalse() {
        assertThat(RegistrationUtil.validateEmail("@icloud.com"))
            .isFalse()
    }

    @Test
    fun validateEmail_worksWithoutNumbers_isTrue() {
        assertThat(RegistrationUtil.validateEmail("joao.pollo@gmail.com"))
            .isTrue()
    }

    @Test
    fun validateUser_lessThan3Characters_isFalse() {
        assertThat(RegistrationUtil.validateUser("us"))
            .isFalse()
    }

    @Test
    fun validateUser_emptyString_isFalse() {
        assertThat(RegistrationUtil.validateUser(""))
            .isFalse()
    }

    @Test
    fun validateUser_moreThan3Characters_isTrue() {
        assertThat(RegistrationUtil.validateUser("user"))
            .isTrue()
    }

    @Test
    fun validateName_emptyString_isFalse() {
        assertThat(RegistrationUtil.validateName(""))
            .isFalse()
    }

    @Test
    fun validateName_nonemptyString_isTrue() {
        assertThat(RegistrationUtil.validateName("V3ryLongName"))
            .isTrue()
    }

    // Make tests for failures of
        // min length of 8 characters √
        // at least one digit √
        // at least one capital letter √
    // Make a test for good matching passwords working √
    
    // make the test for the other functions in the util class with the common failures √
    // common failures and 1 success for each √
}