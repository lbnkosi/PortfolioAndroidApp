package za.co.lbnkosi.portfolio.utilTests

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import za.co.lbnkosi.portfolio.util.validators.EmailValidator.isValidEmail


class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(isValidEmail("name@email.com"));
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(isValidEmail("name@email.co.za"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(isValidEmail(null))
    }

}